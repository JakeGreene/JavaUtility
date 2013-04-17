package ca.jakegreene.util.geometry;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public abstract class GeneralPoint<D extends Dimension<D>> implements Point<D> {
	
	private final D dimension;
	
	public GeneralPoint(D dimension) {
		this.dimension = dimension;
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#add(double)
	 */
	@Override
	public Point<D> add(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			components.add(index, component + scalar);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#add(ca.jakegreene.util.geometry.Point)
	 */
	@Override
	public Point<D> add(Vector<D> other) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double thisComponent = this.get(index);
			double otherComponent = other.get(index);
			components.add(index, thisComponent + otherComponent);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#subtract(double)
	 */
	@Override
	public Point<D> subtract(double scalar) {
		return add(-scalar);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#subtract(ca.jakegreene.util.geometry.Point)
	 */
	@Override
	public Point<D> subtract(Vector<D> other) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double thisComponent = this.get(index);
			double otherComponent = other.get(index);
			components.add(index, thisComponent - otherComponent);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#multiply(double)
	 */
	@Override
	public Point<D> multiply(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			components.add(component * scalar);
		}
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
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#getNumComponents()
	 */
	@Override
	public int size() {
		return dimension.size();
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#getComponent(int)
	 */
	@Override
	public double get(int index) throws IndexOutOfBoundsException {
		return dimension.getComponent(index);
	}
	
	@Override
	public Point<D> copy() {
		return create(dimension.getComponents());
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

	@Override
	public List<Double> components() {
		return dimension.getComponents();
	}
}
