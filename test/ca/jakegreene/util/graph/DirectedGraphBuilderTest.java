package ca.jakegreene.util.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DirectedGraphBuilderTest extends GraphBuilderTest<DirectedEdge<String>> {

	private SimpleGraph.DirectedBuilder<String> builder = SimpleGraph.directedBuilder();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		builder = createBuilder();
	}

	@Test
	public void testAddBiEdge() {
		String source = "V1";
		String destination = "V2";
		builder.addBidirectionalEdge(source, destination);
		SimpleGraph<String, DirectedEdge<String>> graph = builder.build();
		DirectedEdge<String> edge = graph.getEdge(source, destination).get();
		assertTrue("Directed Graph Builder. Adding a bidirectional edge", edge.isBidirectional());
	}

	@Override
	protected SimpleGraph.DirectedBuilder<String> createBuilder() {
		return SimpleGraph.directedBuilder();
	}

}
