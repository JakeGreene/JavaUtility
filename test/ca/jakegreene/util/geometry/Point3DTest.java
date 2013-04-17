package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public class Point3DTest extends PointTest<D3> {
	
	public static final double X = -0.006;
	public static final double Y = -3045.0;
	public static final double Z = 987.45;
	
	public Point3DTest() {
		super(new Point3D(X, Y, Z), 0.1);
	}

	@Override
	protected Point<D3> createTestPoint() {
		return new Point3D(567.0, -765.4, 0.09);
	}
	
	@Override
	protected Vector<D3> createTestVector() {
		return Vector3D.new3DVector(-9.4, 5.3, 4.8);
	}
	
	@Test
	public void testAddSmallerVector() {
		Vector<D2> shifter = Vectors.create2dVector(X, Y);
		List<Double> scalars = shifter.components();
		Point<D3> shifted = point.add(shifter);
		
		String test = "Point Add Smaller Vector";
		checkShifted(shifted, components, scalars, test);
		checkUnchanged(point, components, test);
	}
	
	@Test
	public void testSubSmallerVector() {
		Vector<D2> shifter = Vectors.create2dVector(X, Y);
		
		Vector<D2> negShifter = shifter.multiply(-1);
		List<Double> scalars = negShifter.components();
		
		Point<D3> shifted = point.subtract(shifter);
		
		String test = "Point Subtract Smaller Vector";
		checkShifted(shifted, components, scalars, test);
		checkUnchanged(point, components, test);
	}
	
	@Test
	public void testEqualityDiffType() {
		Point<D3> otherPoint = Points.new3DPoint(X, Y, Z);
		assertEquals("Point2D Equals Point<D2>", point, otherPoint);
	}
	
	@Test
	public void testInequalityBadDimensions() {
		Point<D2> diffPoint = Point2D.newPoint(X, Y);
		assertFalse("Point2D Equals Not the same as different dimension", point.equals(diffPoint));
	}
	
	@Test
	public void testInequalityOneOff() {
		Point3D diffPoint = Point3D.newPoint(X, Y, (Z+1));
		assertFalse("Point2D Equals Not the same as (X, Y, Z')", point.equals(diffPoint));
	}

}
