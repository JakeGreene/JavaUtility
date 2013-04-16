package ca.jakegreene.util.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D1 implements Dimension<D1> {
	
	private final double x;
	
	D1(double x) {
		this.x = x;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public double getComponent(int index) throws IndexOutOfBoundsException {
		if (index == 0) {
			return x;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public List<Double> getComponents() {
		return new ArrayList<Double>(Arrays.asList(x));
	}

	@Override
	public D1 create(List<Double> components) {
		return new D1(components.get(0));
	}

}
