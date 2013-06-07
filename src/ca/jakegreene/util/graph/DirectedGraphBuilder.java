package ca.jakegreene.util.graph;

public interface DirectedGraphBuilder<V> extends GraphBuilder<V, DirectedEdge<V>> {
	public void addBidirectionalEdge(V source, V destination);
}
