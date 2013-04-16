package ca.jakegreene.util.geometry;

public final class Points {
	private Points() {
	}
	
	public static Point<D2> new2DPoint() {
		return new2DPoint(0, 0);
	}
	
	public static Point<D2> new2DPoint(double x, double y) {
		return new GeneralPoint<D2>(new D2(x, y));
	}
	
	public static Point<D2> new2DPointFromAngle(double length, double radAngle) {
		double x = length * Math.cos(radAngle);
		double y = length * Math.sin(radAngle);
		return new GeneralPoint<D2>(new D2(x, y));
	}
	
	public static Point<D3> new3DPoint() {
		return new3DPoint(0, 0, 0);
	}
	
	public static Point<D3> new3DPoint(double x, double y, double z) {
		return new GeneralPoint<D3>(new D3(x, y, z));
	}
	
	public static Point<D3> new3DPointFromAngles(double length, double radIncline, double radAzimuth) {
		double x = length * Math.sin(radIncline) * Math.cos(radAzimuth);
		double y = length * Math.sin(radIncline) * Math.sin(radAzimuth);
		double z = length * Math.cos(radIncline);
		return new3DPoint(x, y, z);
	}
}
