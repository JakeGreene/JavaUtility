package ca.jakegreene.util.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WeightedGraphBuilderTest extends SimpleGraphBuilderTest {
	
	private SimpleGraph.WeightedBuilder<String, SimpleEdge<String>> builder = SimpleGraph.weightedBuilder();

	@Before
	@Override
	public void setUp() throws Exception {
		setBuilder(SimpleGraph.<String>weightedBuilder());
	}
	
	protected void setBuilder(SimpleGraph.WeightedBuilder<String, SimpleEdge<String>> builder) {
		this.builder = builder;
		super.setBuilder(builder);
	}
	
	@Test
	public void testAddWeightedEdge() {
		String source = "V1";
		String destination = "V2";
		builder.addVertex(source);
		builder.addVertex(destination);
		double weight = 2.0;
		builder.addEdge(source, destination, weight);
		Graph<String, SimpleEdge<String>> graph = builder.build();
		assertTrue("Weighted Graph Builder. Can add a weighted edge between two existing vertices", graph.containsEdge(source, destination));
		assertTrue("Weighted Graph Builder. Added weight is retained", graph.getEdge(source, destination).get().weight() == weight);
	}

}
