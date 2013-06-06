package ca.jakegreene.util.graph;

import ca.jakegreene.util.graph.SimpleGraph.Builder;

public class SimpleGraphBuilderTest extends GraphBuilderTest<SimpleEdge<String>>{

	@Override
	protected Builder<String, SimpleEdge<String>> createBuilder() {
		return SimpleGraph.builder();
	}
}
