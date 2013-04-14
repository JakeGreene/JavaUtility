package ca.jakegreene.util.geometry;

import java.util.List;

import com.google.common.collect.Lists;

public class GeneralPoint<D extends Dimension<D>> implements Point<D> {
	
	private final D dimension;
	
	public GeneralPoint(D dimension) {
		this.dimension = dimension;
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#add(double)
	 */
	@Override
	public Point<D> add(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		for (int index = 0; index < getNumComponents(); ++index) {
			double component = components.get(index);
			components.add(index, component + scalar);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#add(ca.jakegreene.util.geometry.Point)
	 */
	@Override
	public Point<D> add(Point<D> other) {
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		for (int index = 0; index < getNumComponents(); ++index) {
			double thisComponent = components.get(index);
			double otherComponent = other.getComponent(index);
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
	public Point<D> subtract(Point<D> other) {
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		for (int index = 0; index < getNumComponents(); ++index) {
			double thisComponent = this.getComponent(index);
			double otherComponent = other.getComponent(index);
			components.add(index, thisComponent - otherComponent);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#multiply(double)
	 */
	@Override
	public Point<D> multiply(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		for (int index = 0; index < getNumComponents(); ++index) {
			double component = components.get(index);
			components.add(component * scalar);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#difference(ca.jakegreene.util.geometry.Point)
	 */
	@Override
	public Vector<D> difference(Point<D> other) {
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		for (int index = 0; index < getNumComponents(); ++index) {
			double diff = getComponent(index) - other.getComponent(index);
			components.add(diff);
		}
		D dimension = this.dimension.create(components);
		return new GeneralVector<D>(dimension);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#getNumComponents()
	 */
	@Override
	public int getNumComponents() {
		return dimension.size();
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#getComponent(int)
	 */
	@Override
	public double getComponent(int index) throws IndexOutOfBoundsException {
		return dimension.getComponent(index);
	}
	
	protected Point<D> create(List<Double> components) {
		D dim = dimension.create(components);
		return new GeneralPoint<D>(dim);
	}
	
	@Override
	public String toString() {
		int components = getNumComponents();
		String msg = components+"D Point: <";
		for (int i = 0; i < components - 1; ++i) {
			msg += getComponent(i) + ", ";
		}
		msg += getComponent(components - 1) + ">";
		return msg;
	}
}
