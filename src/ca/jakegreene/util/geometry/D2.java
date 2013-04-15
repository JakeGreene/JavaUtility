package ca.jakegreene.util.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Second Dimension. D2 contains only two components
 * @author jakegreene
 *
 */
public class D2 implements Dimension<D2> {
	
	private final double x;
	private final double y;
	
	D2() {
		x = 0;
		y = 0;
	}
	
	D2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int size() {
		return 2;
	}

	@Override
	public double getComponent(int index) throws IndexOutOfBoundsException {
		switch(index) {
			case 0: return x;
			case 1: return y;
			default: throw new IndexOutOfBoundsException(D2.class.getCanonicalName() + " does not have a component at index " + index);
		}
	}

	@Override
	public D2 create(List<Double> components) {
		return new D2(components.get(0), components.get(1));
	}

	@Override
	public List<Double> getComponents() {
		return new ArrayList<Double>(Arrays.asList(x, y));
	}

}
