package ca.jakegreene.util.graph;

import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A Simple Graph is a Graph that does not allow
 * multiple edges between vertices and does not allow
 * an edge to start and end on the same vertex (a loop).
 * @author jakegreene
 *
 * @param <V> The vertex type
 * @param <E> The edge type
 */
public class SimpleGraph<V, E extends Edge<V>> implements Graph<V, E> {
	
	private final ImmutableTable<V, V, E> edgeTable;
	private final Set<V> vertices;
	
	private SimpleGraph(ImmutableTable<V, V, E> edges, Set<V> vertices) {
		edgeTable = edges;
		this.vertices = vertices;
	}

	@Override
	public boolean containsVertex(V vertex) {
		return vertices.contains(vertex);
	}

	@Override
	public boolean containsEdge(E edge) {
		return edgeTable.containsValue(edge);
	}

	@Override
	public boolean containsEdge(V source, V destination) {
		Optional<E> edge = getEdge(source, destination);
		return edge.isPresent();
	}

	public Optional<E> getEdge(V source, V destination) {
		E edge = edgeTable.get(source, destination);
		if (edge == null) {
			E reverseEdge = edgeTable.get(destination, source);
			if (reverseEdge == null || !reverseEdge.isBidirectional()) {
				return Optional.absent();
			} else {
				return Optional.of(reverseEdge);
			}
		}
		return Optional.fromNullable(edge);
	}

	@Override
	public List<E> getEdges(V vertex) {
		Set<E> outgoingEdges = outgoingEdges(vertex);
		Set<E> incomingEdges = incomingEdges(vertex);
		return Lists.newArrayList(Sets.union(incomingEdges, outgoingEdges));
	}

	@Override
	public Set<V> getNeighours(V vertex) {
		Set<V> neighbours = Sets.newHashSet();
		/* Everyone on the end of an outgoing edge is
		 * a neighbour.
		 */
		Set<E> outgoingEdges = outgoingEdges(vertex);
		for (E edge : outgoingEdges) {
			neighbours.add(edge.destination());
		}
		
		/*
		 * The source of an incoming edge is a neighbour
		 * iff the edge is bidirectional.
		 */
		Set<E> incomingEdges = incomingEdges(vertex);
		for (E edge : incomingEdges) {
			if (edge.isBidirectional()) {
				neighbours.add(edge.source());
			}
		}
		return neighbours;
	}
	
	private Set<E> outgoingEdges(V vertex) {
		return Sets.newHashSet(edgeTable.row(vertex).values());
	}
	
	private Set<E> incomingEdges(V vertex) {
		return Sets.newHashSet(edgeTable.column(vertex).values());
	}

	@Override
	public Set<V> vertices() {
		return Sets.newHashSet(vertices);
	}

	@Override
	public List<E> edges() {
		return Lists.newArrayList(edgeTable.values());
	}
	
	public static <V> Builder<V, SimpleEdge<V>> builder() {
		return Builder.startGraph();
	}
	
	public static <V> WeightedBuilder<V, SimpleEdge<V>> weightedBuilder() {
		return WeightedBuilder.startGraph();
	}
	
	public static <V> DirectedBuilder<V> directedBuilder() {
		return DirectedBuilder.startDirectedGraph();
	}
	
	public static <V> WeightedDirectedBuilder<V> weightedDirectedBuilder() {
		return WeightedDirectedBuilder.startDirectedGraph();
	}
	
	public static class Builder<V, E extends Edge<V>> extends AbstractGraphBuilder<V, E> {
		
		public Builder(EdgeFactory<V, E> factory) {
			super(factory, new SimpleEdgeStore<V, E>());
		}
		
		public static <V> Builder<V, SimpleEdge<V>> startGraph() {
			return new Builder<V, SimpleEdge<V>>(new SimpleEdge.Factory<V>());
		}	
		
		public SimpleGraph<V, E> build() {
			ImmutableTable.Builder<V, V, E> builder = ImmutableTable.builder();
			for (E edge : store.getEdges()) {
				builder.put(edge.source(), edge.destination(), edge);
			}
			return new SimpleGraph<V, E>(builder.build(), vertices);
		}
	}
	
	public static class WeightedBuilder<V, E extends Edge<V>> extends AbstractWeightedGraphBuilder<V, E> {

		public WeightedBuilder(EdgeFactory<V, E> factory) {
			super(factory, new SimpleEdgeStore<V, E>());
		}
		
		public static <V> WeightedBuilder<V, SimpleEdge<V>> startGraph() {
			return new WeightedBuilder<V, SimpleEdge<V>>(new SimpleEdge.Factory<V>());
		}
		
		public SimpleGraph<V, E> build() {
			ImmutableTable.Builder<V, V, E> builder = ImmutableTable.builder();
			for (E edge : store.getEdges()) {
				builder.put(edge.source(), edge.destination(), edge);
			}
			return new SimpleGraph<V, E>(builder.build(), vertices);
		}
		
	}
	
	public static class DirectedBuilder<V> extends Builder<V, DirectedEdge<V>> implements DirectedGraphBuilder<V> {
		public DirectedBuilder() {
			super(new DirectedEdge.Factory<V>());
		}
		
		public static <V> DirectedBuilder<V> startDirectedGraph() {
			return new DirectedBuilder<V>();
		}
		
		@Override
		public void addBidirectionalEdge(V source, V destination) {
			addVertex(source);
			addVertex(destination);
			addEdge(factory.createBidirectionalEdge(source, destination));
		}
	}
	
	public static class WeightedDirectedBuilder<V> extends DirectedBuilder<V> implements WeightedGraphBuilder<V, DirectedEdge<V>> {
		
		@Override
		public void addEdge(V source, V destination, double weight) {
			addVertex(source);
			addVertex(destination);
			addEdge(factory.createEdge(source, destination, weight));
		}
		
		public void addBidirectionalEdge(V source, V destination, double weight) {
			addVertex(source);
			addVertex(destination);
			addEdge(factory.createBidirectionalEdge(source, destination, weight));
		}
		
		public static <V> WeightedDirectedBuilder<V> startDirectedGraph() {
			return new WeightedDirectedBuilder<V>();
		}
	}

}
