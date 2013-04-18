package ca.jakegreene.util.geometry;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

abstract class GeneralPoint<D extends Dimension> extends CartesianObject<D> implements Point<D> {
	
	public GeneralPoint(D dimension) {
		super(dimension);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#add(double)
	 */
	@Override
	public Point<D> add(double scalar) {
		List<Double> components = addToComponents(scalar);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#add(ca.jakegreene.util.geometry.Point)
	 */
	@Override
	public Point<D> add(Vector<? super D> other) {
		List<Double> components = addToComponents(other);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#subtract(double)
	 */
	@Override
	public Point<D> subtract(double scalar) {
		List<Double> components = subtractFromComponents(scalar);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#subtract(ca.jakegreene.util.geometry.Point)
	 */
	@Override
	public Point<D> subtract(Vector<? super D> other) {
		List<Double> components = subtractFromComponents(other);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#multiply(double)
	 */
	@Override
	public Point<D> multiply(double scalar) {
		List<Double> components = multiplyWithComponents(scalar);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#difference(ca.jakegreene.util.geometry.Point)
	 */
	@Override
	public Vector<D> difference(Point<D> other) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double diff = get(index) - other.get(index);
			components.add(diff);
		}
		return createVector(components);
	}
	
	@Override
	public Point<D> copy() {
		return create(components());
	}
	
	protected abstract Point<D> create(List<Double> components);
	
	protected abstract Vector<D> createVector(List<Double> components);
	
	@Override
	public String toString() {
		int components = size();
		String msg = components+"D Point: <";
		for (int i = 0; i < components - 1; ++i) {
			msg += get(i) + ", ";
		}
		msg += get(components - 1) + ">";
		return msg;
	}
	
	@Override
	public double distanceSquared(Point<D> other) {
		double squareSum = 0;
		for (int index = 0; index < size(); ++index) {
			double delta = other.get(index) - this.get(index);
			squareSum += delta*delta;
		}
		return squareSum;
	}

	@Override
	public double distance(Point<D> other) {
		return Math.sqrt(distanceSquared(other));
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if (! (o instanceof Point)) {
			return false;
		}
		
		
		Point other = (Point)o;
		
		if (size() != other.size()) {
			return false;
		}
		
		for (int index = 0; index < size(); ++index) {
			if (this.get(index) != other.get(index)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		List<Double> components = new LinkedList<Double>();
		for (int index = 0; index < size(); ++index) {
			components.add(get(index));
		}
		return Objects.hashCode(components);
	}

	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#set(int, double)
	 */
	@Override
	public Point<D> set(int index, double value)
			throws IndexOutOfBoundsException {
		List<Double> components = components();
		components.set(index, value);
		return create(components);
	}
}
