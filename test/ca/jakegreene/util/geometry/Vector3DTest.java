package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class Vector3DTest extends VectorTest<D3> {
	
	private static final double X = 234.876;
	private static final double Y = -98712;
	private static final double Z = 0.12345;
	private static final Vector3D vector = new Vector3D(X, Y, Z);
	
	public Vector3DTest() {
		super(vector, 0.1);
	}
	
	@Test
	public void testCross() {
		Vector<D3> other = createTestVector();
		List<Double> otherComponents = other.getComponents();
		Vector3D cross = vector.cross(other);
		
		String test = "Vector3D Cross Product";
		double expectedX = (vector.getY() * other.getComponent(2)) - (vector.getZ() * other.getComponent(1));
		double expectedY = (vector.getZ() * other.getComponent(0)) - (vector.getX() * other.getComponent(2));
		double expectedZ = (vector.getX() * other.getComponent(1)) - (vector.getY() * other.getComponent(0));
		Vector3D expected = Vector3D.new3DVector(expectedX, expectedY, expectedZ);
		assertTrue(test, cross.equals(expected));
		checkUnchanged(vector, components, test);
		checkUnchanged(other, otherComponents, test);
	}
	
	@Test
	public void testEqualityDiff3D() {
		Vector<D3> same = Vectors.create3dVector(X, Y, Z);
		
		assertTrue("Vector3D Equality with Vector<D3>", vector.equals(same));
	}
	
	@Test
	public void testInequalityOneOff() {
		// Assumes X != Y
		Vector3D notSame = Vector3D.new3DVector(X, Y, Y);
		assertFalse("Vector3D Not Equal to unequal Vector", vector.equals(notSame));
	}
	
	@Test 
	public void testInequalityDiffDimensionality() {
		Vector2D notSame = Vector2D.new2DVector(X, Y);
		assertFalse("Vector3D Not Equal to 2D Vector (X, Y)", vector.equals(notSame));
	}

	@Override
	protected Vector<D3> createTestVector() {
		return Vectors.create3dVector((X+1)*5, (Y+2)*7, (Z+3)*11);
	}
}
