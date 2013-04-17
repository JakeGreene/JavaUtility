package ca.jakegreene.util.geometry;

import java.util.List;

/**
 * 
 * @author jakegreene
 *
 * @param <V>
 */
public interface Vector<D extends Dimension> {

	/**
	 * Add the scalar value <code>scalar</code> to this Vector
	 * creating a new Vector such that
	 * e_i = this_i + scalar
	 */
	public Vector<D> add(double scalar);

	/**
	 * Add the components of <code>other</code> to this
	 * to create a new Vector such that element e_i for all i in <code>other</code>
	 * e_i = this_i + other_i
	 * @param other A Vector of similar or lower dimension than this Vector
	 * @return
	 * @throws DimensionalMixException
	 */
	public Vector<D> add(Vector<? super D> other);

	/**
	 * Subtract the scalar value <code>scalar</code> from this Vector
	 * creating a new Vector such that
	 * e_i = this_i - scalar
	 * @param scalar
	 * @return
	 */
	public Vector<D> subtract(double scalar);

	/**
	 * Add the components of <code>other</code> to this
	 * to create a new Vector such that element e_i for all i
	 * e_i = this_i - other_i
	 * @param other
	 * @return
	 */
	public Vector<D> subtract(Vector<D> other);

	/**
	 * Multiply each component of this Vector
	 * by <code>scalar</code> to create a new
	 * Vector such that
	 * e_i = this_i * scalar
	 * @param scalar
	 * @return
	 */
	public Vector<D> multiply(double scalar);

	/**
	 * Find the dot-product of this Vector and <code>other</code>
	 * @param other
	 * @return
	 */
	public double dot(Vector<D> other);

	public double magnitudeSquared();

	public double magnitude();

	/**
	 * Create a unit vector based on this vector
	 * @return
	 */
	public Vector<D> normalize();

	public int size();

	public double get(int index) throws IndexOutOfBoundsException;
	
	public List<Double> components();
	
	/**
	 * Create an exact copy of this Vector such that
	 * this.equals(copy) == true
	 * @return
	 */
	public Vector<D> copy();

}