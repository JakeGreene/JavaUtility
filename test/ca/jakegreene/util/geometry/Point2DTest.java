package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Point2DTest extends PointTest<D2> {
	
	private static final double X = 5.1;
	private static final double Y = 10.8;
	private static final double DELTA = 0.1;
	
	public Point2DTest() {
		super(Point2D.new2DPoint(X, Y), DELTA);
	}
	
	@Test
	public void testEqualityDiffType() {
		Point<D2> otherPoint = Points.new2DPoint(X, Y);
		assertEquals("Point2D Equals Point<D2>", point, otherPoint);
	}
	
	@Test
	public void testInequalityBadDimensions() {
		Point3D diffPoint = Point3D.new3DPoint(X, Y, 0);
		assertFalse("Point2D Equals Not the same as different dimension", point.equals(diffPoint));
	}
	
	@Test
	public void testInequalityOneOff() {
		Point2D diffPoint = Point2D.new2DPoint(X, X);
		assertFalse("Point2D Equals Not the same as (X, X)", point.equals(diffPoint));
	}

	@Override
	protected Point<D2> createTestPoint() {
		return Point2D.new2DPoint((X+1)*5, (Y+1)*7);
	}

}
