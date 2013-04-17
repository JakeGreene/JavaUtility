package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public abstract class PointTest<D extends Dimension> {
	
	protected final Point<D> point;
	protected final List<Double> components;
	protected final double DELTA;
	
	public PointTest(Point<D> point, double delta) {
		this.point = point;
		this.components = point.components();
		this.DELTA = delta;
	}
	
	protected void checkUnchanged(Point<D> point, List<Double> components, String test) {
		for (int index = 0; index < components.size(); ++index) {
			assertEquals(test + ". Component "+ index +" unchanged", components.get(index), point.get(index), DELTA);
		}
	}
	
	private void checkShifted(Point<D> shifted, List<Double> originalComponents, double scalar, String test) {
		for (int index = 0; index < originalComponents.size(); ++index) {
			assertEquals(test + ". Component "+ index +" shifted by "+scalar,  originalComponents.get(index) + scalar, 
																				shifted.get(index), 
																				DELTA);
		}
	}
	
	protected void checkShifted(Point<D> shifted, List<Double> original, List<Double> scalars, String test) {
		for (int index = 0; index < scalars.size(); ++index) {
			assertEquals(test + ". Component "+ index +" shifted by "+scalars.get(index),  original.get(index) + scalars.get(index), 
																							shifted.get(index), 
																							DELTA);
		}
		
		for (int index = scalars.size(); index < shifted.size(); ++index) {
			assertEquals(test + ". Component "+ index +" stayed the same", original.get(index), shifted.get(index), DELTA);
		}
	}
	
	protected abstract Point<D> createTestPoint();
	protected abstract Vector<D> createTestVector();
	
	@Test
	public void testAddScalar() {
		double scalar = 2.3;
		Point<D> shifted = point.add(scalar);
		
		String test = "Point Add Scalar";
		checkShifted(shifted, components, scalar, test);
		checkUnchanged(point, components, test);
	}
	
	@Test
	public void testAddVector() {
		Vector<D> shifter = createTestVector();
		List<Double> scalars = shifter.components();
		Point<D> shifted = point.add(shifter);
		
		String test = "Point Add Vector";
		checkShifted(shifted, components, scalars, test);
		checkUnchanged(point, components, test);
	}
	
	@Test
	public void testSubScalar() {
		double scalar = 2.3;
		Point<D> shifted = point.subtract(scalar);
		
		String test = "Vector Subtract Scalar";
		checkShifted(shifted, components, -scalar, test);
		checkUnchanged(point, components, test);
	}
	
	@Test
	public void testSubVector() {
		Vector<D> shifter = createTestVector();
		Point<D> shifted = point.subtract(shifter);
		
		List<Double>  scalars = shifter.components();
		for (int i = 0; i < scalars.size(); ++i) {
			scalars.set(i, scalars.get(i) * -1);
		}
	
		String test = "Point Subtract Vector";
		checkShifted(shifted, components, scalars, test);
		checkUnchanged(point, components, test);
	}
	
	@Test
	public void testMultScalar() {
		double scalar = 2.3;
		Point<D> scaled = point.multiply(scalar);
		
		String test = "Point Multiply Scalar";
		for (int index = 0; index < scaled.size(); ++index) {
			assertEquals(test +". Component "+ index,   point.get(index) * scalar, 
																		scaled.get(index), 
																		DELTA);
		}
		checkUnchanged(point, components, test);
	}
	
	@Test
	public void testDifference() {
		Point<D> source = createTestPoint();
		List<Double> sourceComponents = source.components();
		Vector<D> v = point.difference(source);
		
		String test = "Point Different";
		for (int index = 0; index < point.size(); ++index) {
			assertEquals(test +". Component "+index, point.get(index) - source.get(index), v.get(index), DELTA);
		}
		checkUnchanged(point, components, test);
		checkUnchanged(source, sourceComponents, test);
	}
	
	@Test
	public void testDistance() {
		Point<D> destination = createTestPoint();
		List<Double> destComponents = destination.components();
		double distance = point.distance(destination);
		
		String test = "Point Distance";
		double distSqrSum = 0;
		for (int index = 0; index < point.size(); ++index) {
			double delta = destination.get(index) - point.get(index);
			distSqrSum += delta*delta;
		}
		double expectedDistance = Math.sqrt(distSqrSum);
		assertEquals(test, expectedDistance, distance, DELTA);
		checkUnchanged(point, components, test);
		checkUnchanged(destination, destComponents, test);
	}
	
	@Test
	public void testDistanceSquared() {
		Point<D> destination = createTestPoint();
		List<Double> destComponents = destination.components();
		double distance = point.distanceSquared(destination);
		
		String test = "Point Distance Squared";
		double expectedDistance = 0;
		for (int index = 0; index < point.size(); ++index) {
			double delta = destination.get(index) - point.get(index);
			expectedDistance += delta*delta;
		}
		assertEquals(test, expectedDistance, distance, DELTA);
		checkUnchanged(point, components, test);
		checkUnchanged(destination, destComponents, test);
	}
	
	@Test
	public void testEquality() {
		Point<D> otherPoint = point.copy();
		assertEquals("Point Equals", point, otherPoint);
	}
	
	@Test
	public void testInequalityNull() {
		assertFalse("Point Equals Not the same as null", point.equals(null));
	}
	
	@Test
	public void testInequalityBadObject() {
		assertFalse("Point Equals Not the same as other class", point.equals(new Object()));
	}
	
	@Test
	public void testHashCode() {
		Point<D> same = point.copy();
		Set<Point<D>> points = new HashSet<Point<D>>();
		points.add(point);
		
		assertTrue("Point HashCode hash(point) == hash(copy(point))", points.contains(same));
	}
	
	@Test
	public void testCopy() {
		Point<D> copy = point.copy();
		assertTrue("Point Copy", point.equals(copy));
		assertTrue("Point Copy not the same object", point != copy);
	}
}
