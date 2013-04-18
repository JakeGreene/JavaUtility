package ca.jakegreene.util.geometry;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

abstract class GeneralVector<D extends Dimension, V extends Vector<D>> extends CartesianObject<D> implements Vector<D> {
	
	GeneralVector(D dimension) {
		super(dimension);
	}
	
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#plus(double)
	 */
	@Override
	public V add(double scalar) {
		List<Double> components = addToComponents(scalar);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#plus(V)
	 */
	@Override
	public V add(Vector<? super D> other) {
		List<Double> components = addToComponents(other);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#minus(double)
	 */
	@Override
	public V subtract(double scalar) {
		List<Double> components = subtractFromComponents(scalar);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#minus(V)
	 */
	@Override
	public V subtract(Vector<? super D> other) {
		List<Double> components = subtractFromComponents(other);
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#multiply(double)
	 */
    @Override
	public V multiply(double scalar) {
    	List<Double> components = multiplyWithComponents(scalar);
    	return create(components);
    }
    
    /* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#dot(V)
	 */
	@Override
	public double dot(Vector<D> other) {
		double dotProduct = 0;
		for (int index = 0; index < size(); ++index) {
			double thisComponent = this.get(index);
			double otherComponent = other.get(index);
			dotProduct += thisComponent*otherComponent;
		}
		return dotProduct;
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#magnitudeSquared()
	 */
	@Override
	public double magnitudeSquared() {
		double magSquared = 0;
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			magSquared += component*component;
		}
		return magSquared;
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#magnitude()
	 */
	@Override
	public double magnitude() {
		return Math.sqrt(magnitudeSquared());
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#normalize()
	 */
	@Override
	public V normalize() {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		double magnitude = magnitude();
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			components.add(index, component / magnitude);
		}
		return create(components);
	}
	
	protected abstract V create(List<Double> components);
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#set(int, double)
	 */
	@Override
	public V set(int index, double value)
			throws IndexOutOfBoundsException {
		List<Double> components = components();
		components.set(index, value);
		return create(components);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if (! (o instanceof Vector)) {
			return false;
		}
		
		Vector other = (Vector)o;
		
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
	public V copy() {
		return create(components());
	}
}
