package ca.jakegreene.util.geometry;

import static org.junit.Assert.assertEquals;

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
		
		assertEquals("Point2D Add Scalar to X", X+scalar, scaledPoint.getComponent(0), DELTA);
		assertEquals("Point2D Add Scalar to Y", Y+scalar, scaledPoint.getComponent(1), DELTA);
		assertEquals("Point2D Add Scalar to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Add Scalar to Y did not change original", Y, point.getY(), DELTA);
	}
	
	@Test
	public void testAddPoint() {
		double xShift = 2.2;
		double yShift = 4.6;
		Point2D shifter = Point2D.new2DPoint(xShift, yShift);
		Point<D2> shiftedPoint = point.add(shifter);
		
		assertEquals("Point2D Add Point to X", X+xShift, shiftedPoint.getComponent(0), DELTA);
		assertEquals("Point2D Add Point to Y", Y+yShift, shiftedPoint.getComponent(1), DELTA);
		assertEquals("Point2D Add Point to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Add Point to Y did not change original", Y, point.getY(), DELTA);
		assertEquals("Point2D Add Point to X did not change shifter", xShift, shifter.getX(), DELTA);
		assertEquals("Point2D Add Point to Y did not change shifter", yShift, shifter.getY(), DELTA);
	}
	
	
	@Test
	public void testSubScalar() {
		double scalar = 2.5;
		Point<D2> scaledPoint = point.subtract(scalar);
		
		assertEquals("Point2D Subtract Scalar to X", X-scalar, scaledPoint.getComponent(0), DELTA);
		assertEquals("Point2D Subtract Scalar to Y", Y-scalar, scaledPoint.getComponent(1), DELTA);
		assertEquals("Point2D Subtract Scalar to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Subtract Scalar to Y did not change original", Y, point.getY(), DELTA);
	}
	
	@Test
	public void testSubPoint() {
		double xShift = 2.2;
		double yShift = 4.6;
		Point2D shifter = Point2D.new2DPoint(xShift, yShift);
		Point<D2> shiftedPoint = point.subtract(shifter);
		
		assertEquals("Point2D Subtract Point to X", X-xShift, shiftedPoint.getComponent(0), DELTA);
		assertEquals("Point2D Subtract Point to Y", Y-yShift, shiftedPoint.getComponent(1), DELTA);
		assertEquals("Point2D Subtract Point to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Subtract Point to Y did not change original", Y, point.getY(), DELTA);
		assertEquals("Point2D Subtract Point to X did not change shifter", xShift, shifter.getX(), DELTA);
		assertEquals("Point2D Subtract Point to Y did not change shifter", yShift, shifter.getY(), DELTA);
	}
	
	@Test
	public void testMultScalar() {
		double scalar = 2.5;
		Point<D2> scaledPoint = point.multiply(scalar);
		
		assertEquals("Point2D Multiply Scalar to X", X*scalar, scaledPoint.getComponent(0), DELTA);
		assertEquals("Point2D Multiply Scalar to Y", Y*scalar, scaledPoint.getComponent(1), DELTA);
		assertEquals("Point2D Multiply Scalar to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Multiply Scalar to Y did not change original", Y, point.getY(), DELTA);
	}
	
	@Test
	public void testDifference() {
		double oX = 6.7;
		double oY = -2.4;
		Point2D source = Point2D.new2DPoint(oX, oY);
		Vector<D2> v = point.difference(source);
		
		assertEquals("Point2D Difference to X", X-oX, v.getComponent(0), DELTA);
		assertEquals("Point2D Difference to Y", Y-oY, v.getComponent(1), DELTA);
		assertEquals("Point2D Difference to X did not change original", X, point.getX(), DELTA);
		assertEquals("Point2D Difference to Y did not change original", Y, point.getY(), DELTA);
		assertEquals("Point2D Difference to X did not change source", oX, source.getX(), DELTA);
		assertEquals("Point2D Difference to Y did not change source", oY, source.getY(), DELTA);
	}

}
