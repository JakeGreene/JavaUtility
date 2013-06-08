package ca.jakegreene.util.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WeightedGraphBuilderTest extends GraphBuilderTest<SimpleEdge<String>> {
	
	private SimpleGraph.WeightedBuilder<String, SimpleEdge<String>> builder = SimpleGraph.weightedBuilder();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		builder = createBuilder();
	}
	
	@Test
	public void testAddWeightedEdge() {
		String source = "V1";
		String destination = "V2";
		builder.addVertex(source);
		builder.addVertex(destination);
		double weight = 2.0;
		builder.addEdge(source, destination, weight);
		SimpleGraph<String, SimpleEdge<String>> graph = builder.build();
		assertTrue("Weighted Graph Builder. Can add a weighted edge between two existing vertices", graph.containsEdge(source, destination));
		assertTrue("Weighted Graph Builder. Added weight is retained", graph.getEdge(source, destination).get().weight() == weight);
	}

	@Override
	protected SimpleGraph.WeightedBuilder<String, SimpleEdge<String>> createBuilder() {
		return SimpleGraph.weightedBuilder();
	}

}
