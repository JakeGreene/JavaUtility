package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class Point2DTest {
	
	private final double X = 5.1;
	private final double Y = 10.8;
	private final double DELTA = 0.1;
	
	private final Point2D point = Point2D.new2DPoint(X, Y);

	@Test
	public void testAccessX() {
		assertEquals("Point2D Accessing X", X, point.getX(), DELTA);
	}
	
	@Test
	public void testAccessY() {
		assertEquals("Point2D Accessing Y", Y, point.getY(), DELTA);
	}
	
	@Test
	public void testAddScalar() {
		double scalar = 2.5;
		Point<D2> scaledPoint = point.add(scalar);
		
		assertEquals("Point2D Add Scalar to X", X+scalar, scaledPoint.get(0), DELTA);
		assertEquals("Point2D Add Scalar to Y", Y+scalar, scaledPoint.get(1), DELTA);
		assertEquals("Point2D Add Scalar to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Add Scalar to Y did not change original", Y, point.getY(), DELTA);
	}
	
	@Test
	public void testAddPoint() {
		double xShift = 2.2;
		double yShift = 4.6;
		Point2D shifter = Point2D.new2DPoint(xShift, yShift);
		Point<D2> shiftedPoint = point.add(shifter);
		
		assertEquals("Point2D Add Point to X", X+xShift, shiftedPoint.get(0), DELTA);
		assertEquals("Point2D Add Point to Y", Y+yShift, shiftedPoint.get(1), DELTA);
		assertEquals("Point2D Add Point to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Add Point to Y did not change original", Y, point.getY(), DELTA);
		assertEquals("Point2D Add Point to X did not change shifter", xShift, shifter.getX(), DELTA);
		assertEquals("Point2D Add Point to Y did not change shifter", yShift, shifter.getY(), DELTA);
	}
	
	
	@Test
	public void testSubScalar() {
		double scalar = 2.5;
		Point<D2> scaledPoint = point.subtract(scalar);
		
		assertEquals("Point2D Subtract Scalar to X", X-scalar, scaledPoint.get(0), DELTA);
		assertEquals("Point2D Subtract Scalar to Y", Y-scalar, scaledPoint.get(1), DELTA);
		assertEquals("Point2D Subtract Scalar to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Subtract Scalar to Y did not change original", Y, point.getY(), DELTA);
	}
	
	@Test
	public void testSubPoint() {
		double xShift = 2.2;
		double yShift = 4.6;
		Point2D shifter = Point2D.new2DPoint(xShift, yShift);
		Point<D2> shiftedPoint = point.subtract(shifter);
		
		assertEquals("Point2D Subtract Point to X", X-xShift, shiftedPoint.get(0), DELTA);
		assertEquals("Point2D Subtract Point to Y", Y-yShift, shiftedPoint.get(1), DELTA);
		assertEquals("Point2D Subtract Point to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Subtract Point to Y did not change original", Y, point.getY(), DELTA);
		assertEquals("Point2D Subtract Point to X did not change shifter", xShift, shifter.getX(), DELTA);
		assertEquals("Point2D Subtract Point to Y did not change shifter", yShift, shifter.getY(), DELTA);
	}
	
	@Test
	public void testMultScalar() {
		double scalar = 2.5;
		Point<D2> scaledPoint = point.multiply(scalar);
		
		assertEquals("Point2D Multiply Scalar to X", X*scalar, scaledPoint.get(0), DELTA);
		assertEquals("Point2D Multiply Scalar to Y", Y*scalar, scaledPoint.get(1), DELTA);
		assertEquals("Point2D Multiply Scalar to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Multiply Scalar to Y did not change original", Y, point.getY(), DELTA);
	}
	
	@Test
	public void testDifference() {
		double oX = 6.7;
		double oY = -2.4;
		Point2D source = Point2D.new2DPoint(oX, oY);
		Vector<D2> v = point.difference(source);
		
		assertEquals("Point2D Difference to X", X-oX, v.get(0), DELTA);
		assertEquals("Point2D Difference to Y", Y-oY, v.get(1), DELTA);
		assertEquals("Point2D Difference to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Difference to Y did not change original", Y, point.getY(), DELTA);
		assertEquals("Point2D Difference to X did not change source", oX, source.getX(), DELTA);
		assertEquals("Point2D Difference to Y did not change source", oY, source.getY(), DELTA);
	}
	
	@Test
	public void testDistance() {
		double destX = 123456.789;
		double destY = -591.374;
		Point2D destination = Point2D.new2DPoint(destX, destY);
		double distance = point.distance(destination);
		
		double deltaX = destX - X;
		double deltaY = destY - Y;
		double expectedDistance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		
		assertEquals("Point2D Distance", expectedDistance, distance, DELTA);
		assertEquals("Point2D Distance did not change original X", X, point.getX(), DELTA);
		assertEquals("Point2D Distance did not change original Y", Y, point.getY(), DELTA);
		assertEquals("Point2D Distance did not change destination X", destX, destination.getX(), DELTA);
		assertEquals("Point2D Distance did not change destination Y", destY, destination.getY(), DELTA);
	}
	
	@Test
	public void testDistanceSquared() {
		double destX = 123456.789;
		double destY = -591.374;
		Point2D destination = Point2D.new2DPoint(destX, destY);
		double distance = point.distanceSquared(destination);
		
		double deltaX = destX - X;
		double deltaY = destY - Y;
		double expectedDistance = deltaX*deltaX + deltaY*deltaY;
		
		assertEquals("Point2D Distance", expectedDistance, distance, DELTA);
		assertEquals("Point2D Distance did not change original X", X, point.getX(), DELTA);
		assertEquals("Point2D Distance did not change original Y", Y, point.getY(), DELTA);
		assertEquals("Point2D Distance did not change destination X", destX, destination.getX(), DELTA);
		assertEquals("Point2D Distance did not change destination Y", destY, destination.getY(), DELTA);
	}
	
	@Test
	public void testEquality() {
		Point2D otherPoint = Point2D.new2DPoint(X, Y);
		assertEquals("Point2D Equals", point, otherPoint);
	}
	
	@Test
	public void testEqualityDiffType() {
		Point<D2> otherPoint = Points.new2DPoint(X, Y);
		assertEquals("Point2D Equals Point<D2>", point, otherPoint);
	}
	
	@Test
	public void testInequalityNull() {
		assertFalse("Point2D Equals Not the same as null", point.equals(null));
	}
	
	@Test
	public void testInequalityBadObject() {
		assertFalse("Point2D Equals Not the same as other class", point.equals(new Object()));
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
	
	@Test
	public void testHashCode() {
		Point2D same = Point2D.new2DPoint(X, Y);
		Set<Point2D> points = new HashSet<Point2D>();
		points.add(point);
		
		assertTrue("Point2D HashCode other points with same (X, Y)", points.contains(same));
	}

}
