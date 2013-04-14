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
}
