package ca.jakegreene.util.graph;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

abstract class AbstractGraphBuilder<V, E extends Edge<V>> implements GraphBuilder<V, E> {

	protected final Set<V> vertices;
	protected final EdgeFactory<V, E> factory;
	protected final EdgeStore<V, E> store;
	
	public AbstractGraphBuilder(EdgeFactory<V, E> factory, EdgeStore<V, E> store) {
		this.vertices = Sets.newHashSet();
		this.factory = factory;
		this.store = store;
	}
	
	@Override
	public void addVertices(Iterable<V> vertices) {
		addVertices(vertices.iterator());
	}
	
	@Override
	public void addVertices(Iterator<V> vertices) {
		while (vertices.hasNext()) {
			addVertex(vertices.next());
		}
	}
	
	@Override
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
	@Override
	public void addEdge(V source, V destination) throws EdgeLoopException {
		if (source.equals(destination)) throw new EdgeLoopException();
		vertices.add(source);
		vertices.add(destination);
		addEdge(factory.createEdge(source, destination));
	}
	
	protected void addEdge(E edge) throws EdgeLoopException {
		store.addEdge(edge);
	}
	
	public abstract Graph<V, E> build();

}
