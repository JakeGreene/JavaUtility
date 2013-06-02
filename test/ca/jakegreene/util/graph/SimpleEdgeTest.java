package ca.jakegreene.util.graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleEdgeTest {
	
	private final String source = "Source";
	private final String destination = "Destination";
	private final SimpleEdge<String> testEdge = SimpleEdge.newEdge(source, destination);

	@Test
	public void testEqualsSelf() {
		assertTrue("Simple Edge. Edge equals self", testEdge.equals(testEdge));
	}
	
	@Test
	public void testEqualsCopy() {
		SimpleEdge<String> copy = SimpleEdge.newEdge(source, destination);
		assertTrue("Simple Edge. Edge equals copy", testEdge.equals(copy));
	}
	
	@Test 
	public void testEqualsSameWeight() {
		SimpleEdge<String> weighted = SimpleEdge.newWeightedEdge(source, destination, testEdge.weight());
		assertTrue("Simple Edge. Edge equals weighted edge with same weight", testEdge.equals(weighted));
	}
	
	@Test
	public void testEqualsFlippedEdge() {
		SimpleEdge<String> flipped = SimpleEdge.newEdge(destination, source);
		assertTrue("Simple Edge. Simple edges A -> B equal B -> A", testEdge.equals(flipped));
	}
	
	@Test
	public void testEqualsBidirectionalEdge() {
		DirectedEdge<String> sameEdge = DirectedEdge.newBidirectionalEdge(source, destination);
		assertTrue("Simple Edge. Edge equals bidirectional edge", testEdge.equals(sameEdge));
	}

	@Test
	public void testDoesNotEqualNull() {
		assertFalse("Simple Edge. Edge does not equal null", testEdge.equals(null));
	}
	
	@Test
	public void testDoesNotEqualObject() {
		assertFalse("Simple Edge. Edge does not equal Object", testEdge.equals(new Object()));
	}
	
	@Test
	public void testDoesNotEqualDifferentSource() {
		SimpleEdge<String> diffEdge = SimpleEdge.newEdge(source+" I'm different", destination);
		assertFalse("Simple Edge. Edge does not equal an edge with a different source", testEdge.equals(diffEdge));
	}
	
	@Test
	public void testDoesNotEqualDifferentDestination() {
		SimpleEdge<String> diffEdge = SimpleEdge.newEdge(source, destination+" I'm different");
		assertFalse("Simple Edge. Edge does not equal an edge with a different destination", testEdge.equals(diffEdge));
	}
	
	@Test
	public void testDoesNotEqualDifferentWeight() {
		SimpleEdge<String> diffEdge = SimpleEdge.newWeightedEdge(source, destination, testEdge.weight() + 1);
		assertFalse("Simple Edge. Edge does not equal an edge with a different weight", testEdge.equals(diffEdge));
	}
	
	@Test
	public void testDefaultWeightIsOne() {
		assertEquals("Simple Edge. Default edge weight is 1.0", 1.0, testEdge.weight(), 0.01);
	}
	
	@Test
	public void testBiDirectional() {
		assertTrue("Simple Edge. Simple Edges are Bidirectional", testEdge.isBidirectional());
	}
	
	@Test
	public void testWeightedIsBidirection() {
		SimpleEdge<String> weighted = SimpleEdge.newWeightedEdge(source, destination, 3.14);
		assertTrue("Simple Edge. Simple Weighted Edges are Bidirectional", weighted.isBidirectional());
	}
}
