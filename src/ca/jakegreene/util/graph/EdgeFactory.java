package ca.jakegreene.util.graph;

public interface EdgeFactory<V, E extends Edge<V>> extends org.jgrapht.EdgeFactory<V, E> {
	public E createEdge(V source, V destination, double weight);
	public E createBidirectionalEdge(V source, V destination);
	public E createBidirectionalEdge(V source, V destination, double weight);
}
