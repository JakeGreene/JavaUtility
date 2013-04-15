package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Vector2DTest {

	private final double X = 12.3;
	private final double Y = -8.99; 
	private final double DELTA = 0.1;
	private final Vector2D vector = Vector2D.new2DVector(X, Y);
	
	@Test
	public void testAccessX() {
		assertEquals("Vector2D Accessing X", X, vector.getX(), DELTA);
	}
	
	@Test
	public void testAccessY() {
		assertEquals("Vector2D Accessing Y", Y, vector.getY(), DELTA);
	}
	
	@Test
	public void testAddScalar() {
		double scalar = 2.3;
		Vector<D2> shifted = vector.add(scalar);
		
		assertEquals("Vector 2D Scalar Shift the X", X+scalar, shifted.getComponent(0), DELTA);
		assertEquals("Vector 2D Scalar Shift the Y", Y+scalar, shifted.getComponent(1), DELTA);
		assertEquals("Vector2D Add Scalar to X did not change original", X, vector.getX(), DELTA);
		assertEquals("Vector2D Add Scalar to Y did not change original", Y, vector.getY(), DELTA);
	}
	
	@Test
	public void testAddVector() {
		double shiftX = -22.33;
		double shiftY = 8899.0;
		Vector2D shifter = Vector2D.new2DVector(shiftX, shiftY);
		Vector<D2> shifted = vector.add(shifter);
		
		assertEquals("Vector2D Add Vector to X", X+shiftX, shifted.getComponent(0), DELTA);
		assertEquals("Vector2D Add Vector to Y", Y+shiftY, shifted.getComponent(1), DELTA);
		assertEquals("Vector2D Add Vector to X did not change original", X, vector.getX(), DELTA);
		assertEquals("Vector2D Add Vector to Y did not change original", Y, vector.getY(), DELTA);
		assertEquals("Vector2D Add Vector to X did not change shifter", shiftX, shifter.getX(), DELTA);
		assertEquals("Vector2D Add Vector to Y did not change shifter", shiftY, shifter.getY(), DELTA);
	}
	
	@Test
	public void testSubScalar() {
		double scalar = 2.3;
		Vector<D2> shifted = vector.subtract(scalar);
		
		assertEquals("Vector 2D Scalar Shift the X", X-scalar, shifted.getComponent(0), DELTA);
		assertEquals("Vector 2D Scalar Shift the Y", Y-scalar, shifted.getComponent(1), DELTA);
		assertEquals("Vector2D Subtract Scalar to X did not change original", X, vector.getX(), DELTA);
		assertEquals("Vector2D Subtract Scalar to Y did not change original", Y, vector.getY(), DELTA);
	}
	
	@Test
	public void testSubVector() {
		double shiftX = -22.33;
		double shiftY = 8899.0;
		Vector2D shifter = Vector2D.new2DVector(shiftX, shiftY);
		Vector<D2> shifted = vector.subtract(shifter);
		
		assertEquals("Vector2D Subtract Vector to X", X-shiftX, shifted.getComponent(0), DELTA);
		assertEquals("Vector2D Subtract Vector to Y", Y-shiftY, shifted.getComponent(1), DELTA);
		assertEquals("Vector2D Subtract Vector to X did not change original", X, vector.getX(), DELTA);
		assertEquals("Vector2D Subtract Vector to Y did not change original", Y, vector.getY(), DELTA);
		assertEquals("Vector2D Subtract Vector to X did not change shifter", shiftX, shifter.getX(), DELTA);
		assertEquals("Vector2D Subtract Vector to Y did not change shifter", shiftY, shifter.getY(), DELTA);
	}
	
	@Test
	public void testMultScalar() {
		double scalar = 2.3;
		Vector<D2> scaled = vector.multiply(scalar);
		
		assertEquals("Vector2D Scalar Scale the X", X*scalar, scaled.getComponent(0), DELTA);
		assertEquals("Vector2D Scalar Scale the Y", Y*scalar, scaled.getComponent(1), DELTA);
		assertEquals("Vector2D Multiply Scalar to X did not change original", X, vector.getX(), DELTA);
		assertEquals("Vector2D Multiply Scalar to Y did not change original", Y, vector.getY(), DELTA);
	}
	
	@Test
	public void testDot() {
		double otherX = -87.45;
		double otherY = 1111.9999;
		Vector2D other = Vector2D.new2DVector(otherX, otherY);
		double dotProduct = vector.dot(other);
		
		assertEquals("Vector2D Dot", (X*otherX) + (Y*otherY), dotProduct, DELTA);
		assertEquals("Vector2D Dot did not change original X", X, vector.getX(), DELTA);
		assertEquals("Vector2D Dot did not change original Y", Y, vector.getY(), DELTA);
	}
	
	@Test
	public void testMagnitudeSquared() {
		double magSquared = vector.magnitudeSquared();
		double expected = X*X + Y*Y;
		
		assertEquals("Vector2D Magnitude Squared", expected, magSquared, DELTA);
		assertEquals("Vector2D MagSquared of Vector did not change original X", X, vector.getX(), DELTA);
		assertEquals("Vector2D MagSquared of Vector did not change original Y", Y, vector.getY(), DELTA);
	}
	
	@Test
	public void testMagnitude() {
		double magnitude = vector.magnitude();
		double expected = Math.sqrt(X*X + Y*Y);
		
		assertEquals("Vector2D Magnitude", expected, magnitude, DELTA);
		assertEquals("Vector2D Magnitude of Vector did not change original X", X, vector.getX(), DELTA);
		assertEquals("Vector2D Magnitude of Vector did not change original Y", Y, vector.getY(), DELTA);
	}
	
	@Test 
	public void testNormalize() {
		Vector<D2> normal = vector.normalize();
		double magnitude = vector.magnitude();
		double expectedX = X / magnitude;
		double expectedY = Y / magnitude;
		
		assertEquals("Vector2D Normalize X", expectedX, normal.getComponent(0), DELTA);
		assertEquals("Vector2D Normalize Y", expectedY, normal.getComponent(1), DELTA);
		assertEquals("Vector2D Normal of Vector did not change original X", X, vector.getX(), DELTA);
		assertEquals("Vector2D Normal of Vector did not change original Y", Y, vector.getY(), DELTA);
	}
	
	@Test
	public void testEquality() {
		Vector2D same = Vector2D.new2DVector(X, Y);
		
		assertTrue("Vector2D Equality", vector.equals(same));
	}
	
	@Test
	public void testEqualityDiff2D() {
		Vector<D2> same = Vectors.create2dVector(X, Y);
		
		assertTrue("Vector2D Equality with Vector<D2>", vector.equals(same));
	}
	
	@Test
	public void testInequalityNull() {
		assertFalse("Vector2D Not Equal to null", vector.equals(null));
	}
	
	@Test
	public void testInequalityNonVector() {
		assertFalse("Vector2D Not Equal to Non-Vector", vector.equals(new Object()));
	}
	
	@Test
	public void testInequalityOneOff() {
		// Assumes X != Y
		Vector2D notSame = Vector2D.new2DVector(X, X);
		assertFalse("Vector2D Not Equal to unequal Vector", vector.equals(notSame));
	}
	
	@Test 
	public void testInequalityDiffDimensionality() {
		Vector3D notSame = Vector3D.new3DVector(X, Y, 0);
		assertFalse("Vector2D Not Equal to 3D Vector (X, Y, 0)", vector.equals(notSame));
	}
	
	@Test
	public void testHashCode() {
		Set<Vector2D> vectors = new HashSet<Vector2D>();
		vectors.add(vector);
		Vector2D same = Vector2D.new2DVector(X, Y);
		
		assertTrue("Vector2D HashCode hash(<X, Y>) == hash(<X, Y>)", vectors.contains(same));
	}
	
}
