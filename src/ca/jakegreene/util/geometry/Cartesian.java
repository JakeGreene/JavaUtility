package ca.jakegreene.util.geometry;

import java.util.List;

interface Cartesian<D extends Dimension> {
	
	public int size();

	public double get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Create a new Cartesian based on this Cartesian but with component <code>index</code>
	 * changed to have the value <code>value</code>
	 * @param index
	 * @param value
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Cartesian<D> set(int index, double value) throws IndexOutOfBoundsException;
	
	/**
	 * A View of this Cartesian's components. Changing the returned list has no effect on this
	 * Cartesian
	 * @return
	 */
	public List<Double> components();
}
