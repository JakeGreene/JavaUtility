package ca.jakegreene.util.geometry;

/**
 * Convenience class for 3D points
 * @author jakegreene
 *
 */
public class Point3D extends GeneralPoint<D3> {
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
	
	public static Point3D new3DPoint() {
		return new Point3D();
	}
	
	public static Point3D new3DPoint(double x, double y, double z) {
		return new Point3D(x, y, z);
	}
}
