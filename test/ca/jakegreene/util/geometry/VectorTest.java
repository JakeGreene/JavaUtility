package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public abstract class VectorTest<D extends Dimension<D>> {
	
	protected final Vector<D> vector;
	protected final List<Double> components;
	protected final double DELTA;
	
	public VectorTest(Vector<D> vector, double delta) {
		this.vector = vector;
		this.DELTA = delta;
		components = vector.components();
	}
	
	protected void checkUnchanged(Vector<D> vector, List<Double> components, String test) {
		for (int index = 0; index < components.size(); ++index) {
			assertEquals(test + ". Component "+ index +" unchanged", components.get(index), vector.get(index), DELTA);
		}
	}
	
	private void checkShifted(Vector<D> shifted, List<Double> originalComponents, double scalar, String test) {
		for (int index = 0; index < originalComponents.size(); ++index) {
			assertEquals(test + ". Components "+ index +" shifted by "+scalar,  originalComponents.get(index) + scalar, 
																				shifted.get(index), 
																				DELTA);
		}
	}
	
	private void checkShifted(Vector<D> shifted, List<Double> original, List<Double> scalars, String test) {
		for (int index = 0; index < original.size(); ++index) {
			assertEquals(test + ". Components "+ index +" shifted by "+scalars.get(index),  original.get(index) + scalars.get(index), 
																				shifted.get(index), 
																				DELTA);
		}
	}
	
	protected abstract Vector<D> createTestVector();
	
	@Test
	public void testAddScalar() {
		double scalar = 2.3;
		Vector<D> shifted = vector.add(scalar);
		
		String test = "Vector Add Scalar";
		checkShifted(shifted, components, scalar, test);
		checkUnchanged(vector, components, test);
	}
	
	@Test
	public void testAddVector() {
		Vector<D> shifter = createTestVector();
		List<Double> scalars = shifter.components();
		Vector<D> shifted = vector.add(shifter);
		
		String test = "Vector Add Vector";
		checkShifted(shifted, components, scalars, test);
		checkUnchanged(vector, components, test);
		checkUnchanged(shifter, scalars, test);
	}
	
	@Test
	public void testSubScalar() {
		double scalar = 2.3;
		Vector<D> shifted = vector.subtract(scalar);
		
		String test = "Vector Subtract Scalar";
		checkShifted(shifted, components, -scalar, test);
		checkUnchanged(vector, components, test);
	}
	
	@Test
	public void testSubVector() {
		Vector<D> shifter = createTestVector();
		List<Double> shiftComponents = shifter.components();
		Vector<D> shifted = vector.subtract(shifter);
		
		List<Double>  scalars = shifter.components();
		for (int i = 0; i < scalars.size(); ++i) {
			scalars.set(i, scalars.get(i) * -1);
		}
		
		String test = "Vector Subtract Vector";
		checkShifted(shifted, components, scalars, test);
		checkUnchanged(vector, components, test);
		checkUnchanged(shifter, shiftComponents, test);
	}
	
	@Test
	public void testMultScalar() {
		double scalar = 2.3;
		Vector<D> scaled = vector.multiply(scalar);
		
		String test = "Vector Multiply Scalar";
		for (int index = 0; index < scaled.size(); ++index) {
			assertEquals(test +". Component "+ index,   vector.get(index) * scalar, 
																		scaled.get(index), 
																		DELTA);
		}

		checkUnchanged(vector, components, test);
	}
	
	@Test
	public void testDot() {
		Vector<D> other = createTestVector();
		List<Double> otherComponents = other.components();
		double dotProduct = vector.dot(other);
		
		double dotExpected = 0;
		for (int index = 0; index < vector.size(); ++index) {
			dotExpected += (vector.get(index) * other.get(index));
		}
		
		String test = "Vector Dot";
		assertEquals(test, dotExpected, dotProduct, DELTA);
		checkUnchanged(vector, components, test);
		checkUnchanged(other, otherComponents, test);
	}
	
	@Test
	public void testMagnitudeSquared() {
		double magSquared = vector.magnitudeSquared();
		
		double expected = 0;
		for (int index = 0; index < vector.size(); ++index) {
			double comp = vector.get(index);
			expected += comp*comp;
		}
		
		String test = "Vector Magnitude Squared";
		assertEquals(test, expected, magSquared, DELTA);
		checkUnchanged(vector, components, test);
	}
	
	@Test
	public void testMagnitude() {
		double magnitude = vector.magnitude();
		
		double expected = 0;
		for (int index = 0; index < vector.size(); ++index) {
			double comp = vector.get(index);
			expected += comp*comp;
		}
		expected = Math.sqrt(expected);
		
		String test = "Vector Magnitude";
		assertEquals(test, expected, magnitude, DELTA);
		checkUnchanged(vector, components, test);
	}
	
	@Test 
	public void testNormalize() {
		Vector<D> normal = vector.normalize();
		
		double magnitude = vector.magnitude();		
		String test = "Vector Normalize";
		for (int index = 0; index < vector.size(); ++index) {
			double expected = vector.get(index) / magnitude;
			assertEquals(test, expected, normal.get(index), DELTA);
		}
		checkUnchanged(vector, components, test);
	}
	
	@Test
	public void testEquality() {
		Vector<D> same = vector.copy();
		
		assertTrue("Vector Equality", vector.equals(same));
	}
	
	@Test
	public void testInequalityNull() {
		assertFalse("Vector Not Equal to null", vector.equals(null));
	}
	
	@Test
	public void testInequalityNonVector() {
		assertFalse("Vector Not Equal to Non-Vector", vector.equals(new Object()));
	}
	
	@Test
	public void testHashCode() {
		Set<Vector<D>> vectors = new HashSet<Vector<D>>();
		vectors.add(vector);
		Vector<D> same = vector.copy();
		
		assertTrue("Vector HashCode hash(v1) == hash(copy(v1))", vectors.contains(same));
	}
	
	@Test
	public void testCopy() {
		Vector<D> copy = vector.copy();
		assertTrue("Vector Copy", vector.equals(copy));
		assertTrue("Vector Copy not the same", vector != copy);
	}

}
