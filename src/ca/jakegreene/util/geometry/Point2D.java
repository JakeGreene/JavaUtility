package ca.jakegreene.util.geometry;

import java.util.List;

/**
 * Convenience class for 2D points
 * @author jakegreene
 *
 */
public class Point2D extends GeneralPoint<D2, Point2D> {
	Point2D() {
		this(0, 0);
	}
	
	Point2D(double x, double y) {
		super(new D2(x, y));
	}
	
	public double getX() {
		return get(0);
	}
	
	public double getY() {
		return get(1);
	}
	
	public static Point2D newPoint() {
		return new Point2D();
	}
	
	public static Point2D newPoint(double x, double y) {
		return new Point2D(x, y);
	}

	@Override
	protected Point2D create(List<Double> components) {
		return new Point2D(components.get(0), components.get(1));
	}

	@Override
	protected Vector<D2> createVector(List<Double> components) {
		return Vectors.create2dVector(components.get(0), components.get(1));
	}
	
}
