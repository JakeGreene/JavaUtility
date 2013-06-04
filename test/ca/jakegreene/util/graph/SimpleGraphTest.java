package ca.jakegreene.util.graph;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class SimpleGraphTest {
	
	private final int NUM_VERTICES = 4;
	private final String base = "V";
	private final SimpleGraph<String, SimpleEdge<String>> graph;
	{
		/*
		 * Create a graph with N vertices such that
		 * E = { (v_i, v_j) if i < j }
		 * In other words, there will be an edge between every vertex in V
		 */
		
		// Create V
		SimpleGraph.Builder<String, SimpleEdge<String>> builder = SimpleGraph.builder();
		for (int i = 0; i < NUM_VERTICES; ++i) {
			builder.addVertex(base+i);
		}
		
		// Create E
		for (int i = 0; i < NUM_VERTICES; ++i) {
			for (int j  = i+1; j < NUM_VERTICES; ++j) {
				builder.addEdge(base+i, base+j);
			}
		}
		
		graph = builder.build();
	}

	@Test
	public void testContainsAddedVertex() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			assertTrue("Simple Graph. Contains reports true for added vertices", graph.containsVertex(base+i));
		}
	}
	
	@Test 
	public void testDoesNotContainNotAddedVertex() {
		for (int i = NUM_VERTICES; i < NUM_VERTICES + 5; ++i) {
			assertFalse("Simple Graph. Contains does not report true for imaginary vertices", graph.containsVertex(base+i));
		}
	}
	
	@Test
	public void testContainsAddedEdges() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			for (int j = i+1; j < NUM_VERTICES; ++j) {
				assertTrue("Simple Graph. Contains reports true for added edge", graph.containsEdge(base+i, base+j));
			}
		}
	}
	
	@Test
	public void testDoesNotContainNotAddedEdges() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			assertFalse("Simple Graph. Contains does not report true for imaginary edges", graph.containsEdge(base+i, base+i));
		}
	}
	
	@Test
	public void testDoesNotContainEdgesBetweenImaginaryVertices() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			for (int j = NUM_VERTICES; j < NUM_VERTICES + 5; ++j) {
				assertFalse("Simple Graph. Does not contain edges between vertices in V and vertices not in V", graph.containsEdge(base+i, base+j));
			}
		}
	}
	
	@Test
	public void testContainsEdgeObjects() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			for (int j = i+1; j < NUM_VERTICES; ++j) {
				Optional<SimpleEdge<String>> possibleEdge = graph.getEdge(base+i, base+j);
				if (possibleEdge.isPresent()) {
					assertTrue("Simple Graph. ContainsEdge(V, V) is the same as ContainsEdge(E)", graph.containsEdge(possibleEdge.get()));
				} else {
					fail("Simple Graph. Egde added with addEdge(V, V) not found with getEdge(V, V)");
				}
			}
		}
	}
	
	@Test
	public void testGetEdges() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			List<SimpleEdge<String>> edges = graph.getEdges(base+i);
			for (int j = i+1; j < NUM_VERTICES; ++j) {
				SimpleEdge<String> edge = graph.getEdge(base+i, base+j).get();
				assertTrue("Simple Graph. getEdges retreives all edges belonging to v", edges.contains(edge));
			}
		}
	}
	
	@Test
	public void testGetEdgesOfImaginaryVertex() {
		List<SimpleEdge<String>> edges = graph.getEdges(base+NUM_VERTICES);
		assertTrue("Simple Graph. There are no edges attatched to an imaginary edge", edges.isEmpty());
	}
	
	@Test
	public void testNoDuplicateEdges() {
		List<SimpleEdge<String>> edges = graph.edges();
		Set<SimpleEdge<String>> depDupEdges = Sets.newHashSet(edges);
		assertTrue("Simple Graph. There are no duplicate edges", edges.size() == depDupEdges.size());
	}
	
	@Test
	public void testBidirectionality() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			for (int j = i+1; j < NUM_VERTICES; ++j) {
				assertTrue("Simple Graph. Contains reports true for reversed added edge", graph.containsEdge(base+j, base+i));
			}
		}
	}
	
	@Test
	public void testBidirectionalyOfEdges() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			for (int j = i+1; j < NUM_VERTICES; ++j) {
				Optional<SimpleEdge<String>> possibleBackwardsEdge = graph.getEdge(base+j, base+i);
				assertTrue("Simple Graph. Contains reports true for revered added edge object", possibleBackwardsEdge.isPresent());
			}
		}
	}

	@Test
	public void testGetNeighbours() {
		for (int i = 0; i < NUM_VERTICES; ++i) {
			Set<String> neighbours = graph.getNeighours(base+i);
			for (int j = i+1; j < NUM_VERTICES; ++j) {
				assertTrue("Simple Graph. Neighbour list contains all neighbours", neighbours.contains(base+j));
			}
		}
	}
}
