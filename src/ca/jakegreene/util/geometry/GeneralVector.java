package ca.jakegreene.util.geometry;

import java.util.List;

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
	public Vector<D> plus(double scalar) {
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
	public Vector<D> plus(Vector<D> other) {
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
	public Vector<D> minus(double scalar) {
		return plus(-scalar);
	}
	
	/* (non-Javadoc)
	 * @see ca.jakegreene.util.geometry.Vector#minus(V)
	 */
	@Override
	public Vector<D> minus(Vector<D> other) {
		return plus(other.multiply(-1));
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
}
