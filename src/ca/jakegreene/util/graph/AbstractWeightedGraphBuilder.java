package ca.jakegreene.util.graph;

abstract class AbstractWeightedGraphBuilder<V, E extends Edge<V>> extends
		AbstractGraphBuilder<V, E> implements WeightedGraphBuilder<V, E> {

	public AbstractWeightedGraphBuilder(EdgeFactory<V, E> factory, EdgeStore<V, E> store) {
		super(factory, store);
	}
	
	@Override
	public void addEdge(V source, V destination, double weight) {
		addVertex(source);
		addVertex(destination);
		addEdge(factory.createEdge(source, destination, weight));
	}
}
