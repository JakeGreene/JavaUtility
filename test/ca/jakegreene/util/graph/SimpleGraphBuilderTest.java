package ca.jakegreene.util.graph;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SimpleGraphBuilderTest {
	
	private SimpleGraph.Builder<String, SimpleEdge<String>> builder;

	@Before
	public void setUp() throws Exception {
		setBuilder(SimpleGraph.<String>builder());
	}
	
	protected void setBuilder(SimpleGraph.Builder<String, SimpleEdge<String>> builder) {
		this.builder = builder;
	}

	@Test
	public void testAddVertex() {
		String vertex = "V1";
		builder.addVertex(vertex);
		Graph<String, SimpleEdge<String>> graph = builder.build();
		assertTrue("Simple Graph Builder. Can add a vertex", graph.containsVertex(vertex));
		assertTrue("Simple Graph Builder. Adding only vertices creates no edges", graph.edges().isEmpty());
	}
	
	@Test
	public void testAddDuplicateVertices() {
		String vertex = "V1";
		String copy = "V1";
		builder.addVertex(vertex);
		builder.addVertex(copy);
		Graph<String, SimpleEdge<String>> graph = builder.build();
		assertTrue("Simple Graph Builder. Adding duplicates only keeps one", graph.vertices().size() == 1);
	}
	
	@Test
	public void testAddVerticeIterable() {
		List<String> vertices = Arrays.asList(new String[]{"V1", "V2"});
		builder.addVertices(vertices);
		SimpleGraph<String, SimpleEdge<String>> graph = builder.build();
		for (String vertex : vertices) {
			assertTrue("Simple Graph Builder. Add an Iterable", graph.containsVertex(vertex));
		}
	}
	
	@Test
	public void testAddVerticeIterator() {
		List<String> vertices = Arrays.asList(new String[]{"V1", "V2"});
		builder.addVertices(vertices.iterator());
		SimpleGraph<String, SimpleEdge<String>> graph = builder.build();
		for (String vertex : vertices) {
			assertTrue("Simple Graph Builder. Add an Iterator", graph.containsVertex(vertex));
		}
	}
	
	@Test
	public void testAddEdge() {
		String source = "V1";
		String destination = "V2";
		builder.addVertex(source);
		builder.addVertex(destination);
		builder.addEdge(source, destination);
		Graph<String, SimpleEdge<String>> graph = builder.build();
		assertTrue("Simple Graph Builder. Can add an edge between two existing vertices", graph.containsEdge(source, destination));
	}
	
	@Test
	public void testAddEdgeBadVertices() {
		String source = "V1";
		String destination = "V2";
		builder.addEdge(source, destination);
		Graph<String, SimpleEdge<String>> graph = builder.build();
		assertTrue("Simple Graph Builder. Can add an edge between two non-existing vertices", graph.containsEdge(source, destination));
		assertTrue("Simple Graph Builder. Adding edge between new vertices adds the vertices", graph.containsVertex(source) && graph.containsVertex(destination));
	}
	
	@Test
	public void testAddDuplicateEdge() {
		String source = "V1";
		String destination = "V2";
		builder.addEdge(source, destination);
		builder.addEdge(source, destination);
		Graph<String, SimpleEdge<String>> graph = builder.build();
		assertTrue("Simple Graph Builder. Duplicate Edge is dropped", graph.getEdges(source).size() == 1);
		assertTrue("Simple Graph Builder. Duplicate Edge is dropped", graph.getEdges(destination).size() == 1);
	}

}
