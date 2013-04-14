package ca.jakegreene.util.geometry;

public final class Vectors {
	private Vectors() {}
	
	public static Vector<D2> create2dVector() {
		return create2dVector(0, 0);
	}
	
	public static Vector<D2> create2dVector(double x, double y) {
		D2 d = new D2(x, y);
		return new GeneralVector<D2>(d);
	}
	
	public static Vector<D3> create3dVector() {
		return create3dVector(0.0, 0.0, 0.0);
	}
	
	public static Vector<D3> create3dVector(double x, double y, double z) {
		D3 d = new D3(x, y, z);
		return new GeneralVector<D3>(d);
	}
	
	public static Vector<D3> create3dVectorWithAngles(double length, double incline, double azimuth) {
		double x = length * Math.sin(incline) * Math.cos(azimuth);
		double y = length * Math.sin(incline) * Math.sin(azimuth);
		double z = length * Math.cos(incline);
		return create3dVector(x, y, z);
	}
	
	public static double getAngle(Vector<D2> vector) {
		return Math.atan2(vector.getComponent(1), vector.getComponent(2));
	}
	
	public static double getIncline(Vector<D3> vector) {
		double magnitude = vector.magnitude();
		if (magnitude == 0) {
			return Double.NaN;
		}
		return Math.acos(vector.getComponent(2) / magnitude);
	}
	
	public static double getAzimuth(Vector<D3> vector) {
		return Math.atan2(vector.getComponent(1), vector.getComponent(0));
	}
	
	public static Vector<D3> cross(Vector<D3> v1, Vector<D3> v2) {
		double x1 = v1.getComponent(0); double x2 = v2.getComponent(0);
		double y1 = v1.getComponent(1); double y2 = v2.getComponent(1);
		double z1 = v1.getComponent(2); double z2 = v2.getComponent(2);
		
		double xPrime = (y1 * z2) - (z1 * y2);
		double yPrime = (z1 * x2) - (x1 * z2);
		double zPrime = (x1 * y2) - (y1 * x2);
		return create3dVector(xPrime, yPrime, zPrime);
	}
}
