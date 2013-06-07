package ca.jakegreene.util.graph;

public interface WeightedGraphBuilder<V, E extends Edge<V>> extends GraphBuilder<V, E> {
	public void addEdge(V source, V destination, double weight);
}
