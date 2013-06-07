package ca.jakegreene.util.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeightedDirectedGraphBuilderTest extends DirectedGraphBuilderTest {

	private SimpleGraph.WeightedDirectedBuilder<String> builder;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		builder = createBuilder();
	}

	@Test
	public void testAddWeightedBiEdge() {
		String source = "V1";
		String destination = "V2";
		double weight = 2.0f;
		builder.addBidirectionalEdge(source, destination, weight);
		SimpleGraph<String, DirectedEdge<String>> graph = builder.build();
		DirectedEdge<String> edge = graph.getEdge(source, destination).get();
		assertTrue("Weighted Directed Graph Builder. Adding a bidirectional edge", edge.isBidirectional());
		assertEquals("Weighted Directed Graph Builder. Correct Weight", edge.weight(), weight, 0.001f);
	}

	@Override
	protected SimpleGraph.WeightedDirectedBuilder<String> createBuilder() {
		return SimpleGraph.weightedDirectedBuilder();
	}
}
