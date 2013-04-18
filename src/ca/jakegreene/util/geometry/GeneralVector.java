package ca.jakegreene.util.geometry;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public abstract class GeneralVector<D extends Dimension> implements Vector<D> {
	
	private final D dimension;
	
	GeneralVector(D dimension) {
		this.dimension = dimension;
	}
	
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#plus(double)
	 */
	@Override
	public Vector<D> add(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			components.add(index, component + scalar);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#plus(V)
	 */
	@Override
	public Vector<D> add(Vector<? super D> other) {
		List<Double> components = components();
		for (int index = 0; index < other.size(); ++index) {
			double myComponent = this.get(index);
			double otherComponent = other.get(index);
			components.set(index, myComponent + otherComponent);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#minus(double)
	 */
	@Override
	public Vector<D> subtract(double scalar) {
		return add(-scalar);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#minus(V)
	 */
	@Override
	public Vector<D> subtract(Vector<? super D> other) {
		return add(other.multiply(-1));
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#multiply(double)
	 */
    @Override
	public Vector<D> multiply(double scalar) {
    	List<Double> components = Lists.newArrayListWithCapacity(size());
    	for (int index = 0; index < size(); ++index) {
    		double component = get(index) * scalar;
    		components.add(index, component);
    	}
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
	public Vector<D> normalize() {
		List<Double> components = Lists.newArrayListWithCapacity(size());
		double magnitude = magnitude();
		for (int index = 0; index < size(); ++index) {
			double component = get(index);
			components.add(index, component / magnitude);
		}
		return create(components);
	}
	
	protected abstract Vector<D> create(List<Double> components);


	@Override
	public int size() {
		return dimension.size();
	}


	@Override
	public double get(int index) throws IndexOutOfBoundsException {
		return dimension.getComponent(index);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Point#set(int, double)
	 */
	@Override
	public Vector<D> set(int index, double value)
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
	public List<Double> components() {
		return dimension.getComponents();
	}


	@Override
	public Vector<D> copy() {
		return create(dimension.getComponents());
	}
}
