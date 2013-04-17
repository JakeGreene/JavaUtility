package ca.jakegreene.util.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Third Dimension. D3 contains only three components
 * @author jakegreene
 *
 */
public class D3 extends D2 {

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
	public List<Double> getComponents() {
		return new ArrayList<Double>(Arrays.asList(x, y, z));
	}

}
