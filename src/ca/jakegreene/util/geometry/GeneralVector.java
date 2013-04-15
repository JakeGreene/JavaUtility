package ca.jakegreene.util.geometry;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class GeneralVector<D extends Dimension<D>> implements Vector<D> {
	
	private final D dimension;
	
	GeneralVector(D dimension) {
		this.dimension = dimension;
	}
	
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#plus(double)
	 */
	@Override
	public Vector<D> add(double scalar) {
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		for (int index = 0; index < getNumComponents(); ++index) {
			double component = getComponent(index);
			components.add(index, component + scalar);
		}
		return create(components);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#plus(V)
	 */
	@Override
	public Vector<D> add(Vector<D> other) {
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		for (int index = 0; index < getNumComponents(); ++index) {
			double myComponent = this.getComponent(index);
			double otherComponent = other.getComponent(index);
			components.add(index, myComponent + otherComponent);
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
	public Vector<D> subtract(Vector<D> other) {
		return add(other.multiply(-1));
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#multiply(double)
	 */
    @Override
	public Vector<D> multiply(double scalar) {
    	List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
    	for (int index = 0; index < getNumComponents(); ++index) {
    		double component = getComponent(index) * scalar;
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
		for (int index = 0; index < getNumComponents(); ++index) {
			double thisComponent = this.getComponent(index);
			double otherComponent = other.getComponent(index);
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
		for (int index = 0; index < getNumComponents(); ++index) {
			double component = getComponent(index);
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
		List<Double> components = Lists.newArrayListWithCapacity(getNumComponents());
		double magnitude = magnitude();
		for (int index = 0; index < getNumComponents(); ++index) {
			double component = getComponent(index);
			components.add(index, component / magnitude);
		}
		return create(components);
	}
	
	protected Vector<D> create(List<Double> components) {
		return new GeneralVector<D>(dimension.create(components));
	}


	@Override
	public int getNumComponents() {
		return dimension.size();
	}


	@Override
	public double getComponent(int index) throws IndexOutOfBoundsException {
		return dimension.getComponent(index);
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
		
		if (getNumComponents() != other.getNumComponents()) {
			return false;
		}
		
		for (int index = 0; index < getNumComponents(); ++index) {
			if (this.getComponent(index) != other.getComponent(index)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		List<Double> components = new LinkedList<Double>();
		for (int index = 0; index < getNumComponents(); ++index) {
			components.add(getComponent(index));
		}
		return Objects.hashCode(components);
	}


	@Override
	public List<Double> getComponents() {
		return dimension.getComponents();
	}


	@Override
	public Vector<D> copy() {
		return new GeneralVector<D>(dimension);
	}
}
