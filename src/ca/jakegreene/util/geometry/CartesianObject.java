package ca.jakegreene.util.geometry;

import java.util.List;

import com.google.common.collect.Lists;

abstract class CartesianObject<D extends Dimension> implements Cartesian<D> {
	
	private final D coordinates;
	
	protected CartesianObject(D coord) {
		coordinates = coord;
	}
	
	protected List<Double> addToComponents(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			components.add(index, component + scalar);
		}
		return components;
	}
	
	protected List<Double> addToComponents(Cartesian<? super D> other) {
		List<Double> components = components();
		for (int index = 0; index < other.size(); ++index) {
			double thisComponent = this.get(index);
			double otherComponent = other.get(index);
			components.set(index, thisComponent + otherComponent);
		}
		return components;
	}
	
	protected List<Double> subtractFromComponents(double scalar) {
		return addToComponents(-scalar);
	}
	
	protected List<Double> subtractFromComponents(Cartesian<? super D> other) {
		List<Double> components = components();
		for (int index = 0; index < other.size(); ++index) {
			double thisComponent = this.get(index);
			double otherComponent = other.get(index);
			components.set(index, thisComponent - otherComponent);
		}
		return components;
	}
	
	protected List<Double> multiplyWithComponents(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			components.add(component * scalar);
		}
		return components;
	}

	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Cartesian#size()
	 */
	@Override
	public int size() {
		return coordinates.size();
	}

	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Cartesian#get(int)
	 */
	@Override
	public double get(int index) throws IndexOutOfBoundsException {
		return coordinates.getComponent(index);
	}

	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Cartesian#components()
	 */
	@Override
	public List<Double> components() {
		return coordinates.getComponents();
	}

}
