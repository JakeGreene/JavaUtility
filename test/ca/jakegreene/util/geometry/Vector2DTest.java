package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Vector2DTest extends VectorTest<D2> {
	
	
	private static final double X = 234.876;
	private static final double Y = -98712;
	private static final Vector2D vector = new Vector2D(X, Y);
	
	public Vector2DTest() {
		super(vector, 0.1);
	}
	
	@Test
	public void testEqualityDiff2D() {
		Vector<D2> same = Vectors.create2dVector(X, Y);
		
		assertTrue("Vector2D Equality with Vector<D2>", vector.equals(same));
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

	@Override
	protected Vector<D2> createTestVector() {
		return Vectors.create2dVector((X+1)*5, (Y+2)*7);
	}
	
}
