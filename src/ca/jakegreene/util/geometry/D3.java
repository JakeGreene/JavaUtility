package ca.jakegreene.util.geometry;

import java.util.List;

/**
 * Third Dimension. D3 contains only three components
 * @author jakegreene
 *
 */
public class D3 implements Dimension<D3> {

	private final double x;
	private final double y;
	private final double z;
	
	D3() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	D3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public int size() {
		return 3;
	}

	public double getComponent(int index) throws IndexOutOfBoundsException {
		switch(index) {
			case 0: return x;
			case 1: return y;
			case 2: return z;
			default: throw new IndexOutOfBoundsException(D3.class.getCanonicalName() + " does not have a component at index " + index);
		}
	}

	@Override
	public D3 create(List<Double> components) {
		return new D3(components.get(0), components.get(1), components.get(2));
	}

}
