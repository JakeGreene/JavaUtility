package ca.jakegreene.util.graph;

import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

/**
 * A SimpleEdgeStore is an EdgeStore that only allows one edge per vertex pair
 * @author jakegreene
 *
 * @param <V> The Vertex Type
 * @param <E> The Edge Type
 */
public class SimpleEdgeStore<V, E extends Edge<V>> implements EdgeStore<V, E> {

	Table<V, V, E> edges;
	
	SimpleEdgeStore() {
		edges = HashBasedTable.create();
	}
	
	@Override
	public void addEdge(E edge) {
		/* We need to remove all the edges that are similar to the new
		 * edge. This includes existing (source - destination) and 
		 * (destination <-> source)
		 */
		removeEdge(edge);
		edges.put(edge.source(), edge.destination(), edge);
	}

	@Override
	public void removeEdge(E edge) {
		V source = edge.source();
		V destination  = edge.destination();
		E possibleEdge = edges.get(source, destination);
		if (possibleEdge != null && possibleEdge.equals(edge)) {
			edges.remove(source, destination);
		}
		
		/* if edge is bidirectional then the reverse edge (dest -> source)
		 * may need to be removed
		 */
		
		E reverseEdge = edges.get(destination, source);
		if (edge.equals(reverseEdge)) {
			edges.remove(destination, source);
		}
	}

	@Override
	public void removeEdges(V source, V destination) {
		edges.remove(source, destination);
		E edge = edges.get(destination, source);
		if (edge != null && edge.isBidirectional()) {
			edges.remove(destination, source);
		}
	}

	@Override
	public boolean contains(E edge) {
		V source = edge.source();
		V destination = edge.destination();
		E forwardMatch = edges.get(source, destination);
		E backwardsMatch = edges.get(destination, source);
		return (edge.equals(forwardMatch) || edge.equals(backwardsMatch));
	}

	@Override
	public boolean contains(V source, V destination) {
		E forwardMatch = edges.get(source, destination);
		E backwardsMatch = edges.get(destination, source);
		if (forwardMatch != null) {
			return true;
		} else if (backwardsMatch != null && backwardsMatch.isBidirectional()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<E> getEdges(V source, V destination) {
		/*
		 *  There will only be one edge is in this Set by
		 *  the definition of SimpleEdgeStore
		 */
		Set<E> foundEdges = Sets.newHashSet();
		Optional<E> possibleEdge = getEdge(source, destination);
		if (possibleEdge.isPresent()) {
			foundEdges.add(possibleEdge.get());
		}
		return foundEdges;
	}
	
	public Optional<E> getEdge(V source, V destination) {
		E edge = edges.get(source, destination);
		if (edge != null) {
			return Optional.of(edge);
		}
		// The reverse edge (dest -> source) will be usable if it is bidirectional
		E reverseEdge = edges.get(destination, source);
		if (reverseEdge != null && reverseEdge.isBidirectional()) {
			return Optional.of(reverseEdge);
		}
		return Optional.absent();
	}

	@Override
	public Set<E> getEdges() {
		/* None of the edges can be duplicates by the
		 * definition of SimpleEdgeStore.
		 */
		return Sets.newHashSet(edges.values());
	}

	@Override
	public Set<E> getOutgoingEdges(V source) {
		return Sets.newHashSet(edges.row(source).values());
	}

	@Override
	public Set<E> getIncomingEdges(V destination) {
		return Sets.newHashSet(edges.column(destination).values());
	}
}
