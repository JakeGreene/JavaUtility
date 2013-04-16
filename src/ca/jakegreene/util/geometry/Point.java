package ca.jakegreene.util.geometry;

import java.util.List;

public interface Point<D extends Dimension<D>> {

	/**
	 * Add this Point to <code>scalar</code> creating a new Point such that
	 * e_i = this_i + scalar
	 * @param scalar
	 * @return
	 */
	public Point<D> add(double scalar);

	/**
	 * Add <code>other</code> to <code>this</code> Point creating a new Point such that
	 * e_i = this_i + other_i
	 * @param other
	 * @return
	 */
	public Point<D> add(Vector<D> other);

	/**
	 * Subtract this Point and <code>scalar</code> creating a new Point such that
	 * e_i = this_i - scalar
	 * @param scalar
	 * @return
	 */
	public Point<D> subtract(double scalar);

	/**
	 * Subtract this Point and <code>other</code> creating a new Point such that
	 * e_i = this_i - other_i
	 * @param other
	 * @return
	 */
	public Point<D> subtract(Point<D> other);

	/**
	 * Multiply this Point with <code>scalar</code> creating a new Point such that
	 * e_i = this_i * scalar
	 * @param other
	 * @return
	 */
	public Point<D> multiply(double scalar);

	/**
	 * Create a new Vector of the same dimensionality as <code>this</code> that 
	 * spans the distance <b>from</b> <code>other</code> <b>to</b> <code>this</code>
	 * i.e. <code>other</code> is the source and <code>this</code> is the destination.
	 * @param other
	 * @return
	 */
	public Vector<D> difference(Point<D> other);
	
	/**
	 * Find the squared Euclidean distance from <code>this</code> to <code>other</code>.
	 * @param other
	 * @return
	 */
	public double distanceSquared(Point<D> other);
	
	/**
	 * Find the Euclidean distance from <code>this</code> to <code>other</code>
	 * @param other
	 * @return
	 */
	public double distance(Point<D> other);

	public int size();

	public double get(int index) throws IndexOutOfBoundsException;
	
	public List<Double> components();
	
	public Point<D> copy();
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public int hashCode();

}