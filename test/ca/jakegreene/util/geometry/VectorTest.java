package ca.jakegreene.util.geometry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

public abstract class VectorTest<D extends Dimension<D>> {
	
	protected final Vector<D> vector;
	protected final List<Double> components;
	protected final double DELTA;
	
	public VectorTest(Vector<D> vector, double delta) {
		this.vector = vector;
		this.DELTA = delta;
		components = new ArrayList<Double>();
		for (int index = 0; index < vector.getNumComponents(); ++index) {
			components.add(index, vector.getComponent(index));
		}
	}
	
	protected void checkUnchanged(Vector<D> vector, List<Double> components, String test) {
		for (int index = 0; index < components.size(); ++index) {
			assertEquals(test + ". Component "+ index +" unchanged", components.get(index), vector.getComponent(index), DELTA);
		}
	}
	
	private void checkShifted(Vector<D> shifted, List<Double> originalComponents, double scalar, String test) {
		for (int index = 0; index < originalComponents.size(); ++index) {
			assertEquals(test + ". Components "+ index +" shifted by "+scalar,  originalComponents.get(index) + scalar, 
																				shifted.getComponent(index), 
																				DELTA);
		}
	}
	
	private void checkShifted(Vector<D> shifted, List<Double> original, List<Double> scalars, String test) {
		for (int index = 0; index < original.size(); ++index) {
			assertEquals(test + ". Components "+ index +" shifted by "+scalars.get(index),  original.get(index) + scalars.get(index), 
																				shifted.getComponent(index), 
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
		List<Double> scalars = shifter.getComponents();
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
		List<Double> shiftComponents = shifter.getComponents();
		Vector<D> shifted = vector.subtract(shifter);
		
		List<Double>  scalars = shifter.getComponents();
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
		for (int index = 0; index < scaled.getNumComponents(); ++index) {
			assertEquals(test +". Component "+ index,   vector.getComponent(index) * scalar, 
																		scaled.getComponent(index), 
																		DELTA);
		}

		checkUnchanged(vector, components, test);
	}
	
	@Test
	public void testDot() {
		Vector<D> other = createTestVector();
		List<Double> otherComponents = other.getComponents();
		double dotProduct = vector.dot(other);
		
		double dotExpected = 0;
		for (int index = 0; index < vector.getNumComponents(); ++index) {
			dotExpected += (vector.getComponent(index) * other.getComponent(index));
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
		for (int index = 0; index < vector.getNumComponents(); ++index) {
			double comp = vector.getComponent(index);
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
		for (int index = 0; index < vector.getNumComponents(); ++index) {
			double comp = vector.getComponent(index);
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
		for (int index = 0; index < vector.getNumComponents(); ++index) {
			double expected = vector.getComponent(index) / magnitude;
			assertEquals(test, expected, normal.getComponent(index), DELTA);
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
