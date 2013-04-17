package ca.jakegreene.util.geometry;

import java.util.List;

/**
 * Convenience class for 3D Vectors. Includes 3D specific functionality
 * @author jakegreene
 *
 */
public class Vector3D extends GeneralVector<D3> {

	Vector3D() {
		this(0, 0, 0);
	}
	
	Vector3D(double x, double y, double z) {
		super(new D3(x, y, z));
	}
	
	public static Vector3D new3DVector() {
		return new Vector3D();
	}
	
	public static Vector3D new3DVector(double x, double y, double z) {
		return new Vector3D(x, y, z);
	}
	
	/**
	 * Create a new 3D Vector with length <code>length</code>, incline/polar angle (angle from Z-Axis)
	 * <code>radIncline</code>, and azimuth (angle from X-Axis) <code>radAzimuth</code> 
	 * @param length
	 * @param radIncline Angle from Z-Axis in radians
	 * @param radAzimuth Angle from X-Axis in radians
	 * @return
	 */
	public static Vector3D new3DVectorFromAngles(double length, double radIncline, double radAzimuth) {
		double x = length * Math.sin(radIncline) * Math.cos(radAzimuth);
		double y = length * Math.sin(radIncline) * Math.sin(radAzimuth);
		double z = length * Math.cos(radIncline);
		return new Vector3D(x, y, z);
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
	
	public Vector3D cross(Vector<D3> other) {
		double x1 = getX(); double x2 = other.get(0);
		double y1 = getY(); double y2 = other.get(1);
		double z1 = getZ(); double z2 = other.get(2);
		
		double xPrime = (y1 * z2) - (z1 * y2);
		double yPrime = (z1 * x2) - (x1 * z2);
		double zPrime = (x1 * y2) - (y1 * x2);
		return new Vector3D(xPrime, yPrime, zPrime);
	}
	
	public static Vector3D newVector() {
		return newVector(0, 0, 0);
	}
	
	public static Vector3D newVector(double x, double y, double z) {
		return new Vector3D(x, y, z);
	}
	
	public static Vector3D newVectorFromAngles(double length, double radIncline, double radAzimuth) {
		double x = length * Math.sin(radIncline) * Math.cos(radAzimuth);
		double y = length * Math.sin(radIncline) * Math.sin(radAzimuth);
		double z = length * Math.cos(radIncline);
		return new Vector3D(x, y, z);
	}

	@Override
	protected Vector<D3> create(List<Double> components) {
		return new Vector3D(components.get(0), components.get(1), components.get(2));
	}
}
