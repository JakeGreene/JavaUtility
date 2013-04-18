package ca.jakegreene.util.geometry;

import java.util.List;

/**
 * Convenience class for 3D points
 * @author jakegreene
 *
 */
public class Point3D extends GeneralPoint<D3, Point3D> {
	Point3D() {
		this(0, 0, 0);
	}
	
	Point3D(double x, double y, double z) {
		super(new D3(x, y, z));
	}
	
	public double getX() {
		return get(0);
	}
	
	public double getY() {
		return get(1);
	}
	
	public double getZ() {
		return get(2);
	}
	
	public static Point3D newPoint() {
		return new Point3D();
	}
	
	public static Point3D newPoint(double x, double y, double z) {
		return new Point3D(x, y, z);
	}
	
	@Override
	protected Point3D create(List<Double> components) {
		return new Point3D(components.get(0), components.get(1), components.get(2));
	}

	@Override
	protected Vector<D3> createVector(List<Double> components) {
		return Vectors.create3dVector(components.get(0), components.get(1), components.get(2));
	}
}
