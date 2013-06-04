package ca.jakegreene.util.graph;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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

	@Override
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
	
	public static class Builder<V, E extends Edge<V>> {
		
		private ImmutableTable.Builder<V, V, E> builder;
		private Set<V> vertices;
		private final EdgeFactory<V, E> factory;
		
		public Builder(EdgeFactory<V, E> factory) {
			builder = ImmutableTable.builder();
			vertices = Sets.newHashSet();
			this.factory = factory;
		}
		
		public static <V> Builder<V, SimpleEdge<V>> startGraph() {
			return new Builder<V, SimpleEdge<V>>(new SimpleEdge.Factory<V>());
		}
		
		public void addVertices(Iterable<V> vertices) {
			addVertices(vertices.iterator());
		}
		
		public void addVertices(Iterator<V> vertices) {
			while (vertices.hasNext()) {
				addVertex(vertices.next());
			}
		}
		
		public void addVertex(V vertex) {
			vertices.add(vertex);
		}
		
		/**
		 * Add an edge between the source and destination.
		 * The vertices will be added to the graph if they don't
		 * already exist
		 * @param source
		 * @param destination
		 */
		public void addEdge(V source, V destination) {
			builder.put(source, destination, factory.createEdge(source, destination));
			vertices.add(source);
			vertices.add(destination);
		}
		
		public SimpleGraph<V, E> build() {
			return new SimpleGraph<V, E>(builder.build(), vertices);
		}
	}

}
