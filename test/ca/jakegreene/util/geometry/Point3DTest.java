package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
	
	@Test
	public void testEqualityDiffType() {
		Point<D3> otherPoint = Points.new3DPoint(X, Y, Z);
		assertEquals("Point2D Equals Point<D2>", point, otherPoint);
	}
	
	@Test
	public void testInequalityBadDimensions() {
		Point<D2> diffPoint = Point2D.new2DPoint(X, Y);
		assertFalse("Point2D Equals Not the same as different dimension", point.equals(diffPoint));
	}
	
	@Test
	public void testInequalityOneOff() {
		Point3D diffPoint = Point3D.new3DPoint(X, Y, (Z+1));
		assertFalse("Point2D Equals Not the same as (X, Y, Z')", point.equals(diffPoint));
	}

}
