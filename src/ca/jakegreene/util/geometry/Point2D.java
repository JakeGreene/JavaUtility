package ca.jakegreene.util.geometry;

/**
 * Convenience class for 2D points
 * @author jakegreene
 *
 */
public class Point2D extends GeneralPoint<D2> {
	Point2D() {
		this(0, 0);
	}
	
	Point2D(double x, double y) {
		super(new D2(x, y));
	}
	
	public double getX() {
		return getComponent(0);
	}
	
	public double getY() {
		return getComponent(1);
	}
	
	public static Point2D new2DPoint() {
		return new Point2D();
	}
	
	public static Point2D new2DPoint(double x, double y) {
		return new Point2D(x, y);
	}
	
}
