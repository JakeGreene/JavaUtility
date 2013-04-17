package ca.jakegreene.util.geometry;

import java.util.List;

/**
 * Convenience class for 2D Vectors
 * @author jakegreene
 *
 */
public class Vector2D extends GeneralVector<D2> {
	Vector2D() {
		this(0, 0);
	}
	
	Vector2D(double x, double y) {
		super(new D2(x, y));
	}
	
	public double getX() {
		return get(0);
	}
	
	public double getY() {
		return get(1);
	}
	
	public double getAngle() {
		return Math.atan2(getY(), getX());
	}
	
	public static Vector2D newVector() {
		return new Vector2D();
	}
	
	public static Vector2D newVector(double x, double y) {
		return new Vector2D(x, y);
	}
	
	public static Vector2D newVectorFromAngle(double length, double radAngle) {
		double x = length * Math.cos(radAngle);
		double y = length * Math.sin(radAngle);
		return new Vector2D(x, y);
	}

	@Override
	protected Vector<D2> create(List<Double> components) {
		return new Vector2D(components.get(0), components.get(1));
	}
}
