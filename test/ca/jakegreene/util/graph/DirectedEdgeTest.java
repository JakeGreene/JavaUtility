package ca.jakegreene.util.graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectedEdgeTest {
	
	private final String source = "Source";
	private final String destination = "Destination";
	private final DirectedEdge<String> testEdge = DirectedEdge.newEdge(source, destination);

	@Test
	public void testEqualsSelf() {
		assertTrue("Directed Edge. Edge equals self", testEdge.equals(testEdge));
	}
	
	@Test
	public void testEqualsCopy() {
		DirectedEdge<String> copy = DirectedEdge.newEdge(source, destination);
		assertTrue("Directed Edge. Edge equals copy", testEdge.equals(copy));
	}
	
	@Test 
	public void testEqualsSameWeight() {
		DirectedEdge<String> weighted = DirectedEdge.newWeightedEdge(source, destination, testEdge.weight());
		assertTrue("Directed Edge. Edge equals weighted edge with same weight", testEdge.equals(weighted));
	}
	
	@Test
	public void testEqualsSimpleEdge() {
		DirectedEdge<String> biEdge = DirectedEdge.newBidirectionalEdge(source, destination);
		SimpleEdge<String> sameEdge = SimpleEdge.newEdge(source, destination);
		assertTrue("Directed Edge. Bidirectional edge equals simple edge", biEdge.equals(sameEdge));
	}
	
	@Test
	public void testBidirectionalEqualsFlipped() {
		DirectedEdge<String> biEdge = DirectedEdge.newBidirectionalEdge(source, destination);
		DirectedEdge<String> flipped = DirectedEdge.newBidirectionalEdge(destination, source);
		assertTrue("Directed Edge. Bidirectional edge equals its flipped copy", biEdge.equals(flipped));
	}
	
	@Test
	public void testDoesNotEqualFlippedEdge() {
		DirectedEdge<String> flipped = DirectedEdge.newEdge(destination, source);
		assertFalse("Directed Edge. Directed Edge (A -> B) does not equal (B -> A)", testEdge.equals(flipped));
	}
	
	@Test
	public void testDoesNotEqualBidirectionalForward() {
		DirectedEdge<String> biEdge = DirectedEdge.newBidirectionalEdge(source, destination);
		assertFalse("Directed Edge. Directed Edge (A -> B) does not equal (A <-> B)", testEdge.equals(biEdge));
	}
	
	@Test
	public void testDoesNotEqualBidirectionalBackward() {
		DirectedEdge<String> biEdge = DirectedEdge.newBidirectionalEdge(destination, source);
		assertFalse("Directed Edge. Directed Edge (A -> B) does not equal (A <-> B)", testEdge.equals(biEdge));
	}

	@Test
	public void testDoesNotEqualNull() {
		assertFalse("Directed Edge. Edge does not equal null", testEdge.equals(null));
	}
	
	@Test
	public void testDoesNotEqualObject() {
		assertFalse("Directed Edge. Edge does not equal Object", testEdge.equals(new Object()));
	}
	
	@Test
	public void testDoesNotEqualDifferentSource() {
		DirectedEdge<String> diffEdge = DirectedEdge.newEdge(source+" I'm different", destination);
		assertFalse("Directed Edge. Edge does not equal an edge with a different source", testEdge.equals(diffEdge));
	}
	
	@Test
	public void testDoesNotEqualDifferentDestination() {
		DirectedEdge<String> diffEdge = DirectedEdge.newEdge(source, destination+" I'm different");
		assertFalse("Directed Edge. Edge does not equal an edge with a different destination", testEdge.equals(diffEdge));
	}
	
	@Test
	public void testDoesNotEqualDifferentWeight() {
		DirectedEdge<String> diffEdge = DirectedEdge.newWeightedEdge(source, destination, testEdge.weight() + 1);
		assertFalse("Directed Edge. Edge does not equal an edge with a different weight", testEdge.equals(diffEdge));
	}
	
	@Test
	public void testDefaultWeightIsOne() {
		assertEquals("Directed Edge. Default edge weight is 1.0", 1.0, testEdge.weight(), 0.01);
	}
	
	@Test
	public void testNotBidirectional() {
		assertFalse("Directed Edge. Directed edges are not bidirectional unless defined otherwise", testEdge.isBidirectional());
	}
	
	@Test
	public void testBidirectionalAreBidirectional() {
		DirectedEdge<String> biEdge = DirectedEdge.newBidirectionalEdge(source, destination);
		assertTrue("Directed Edge. Bidirectional edges report as being bidirectional", biEdge.isBidirectional());
	}

}
