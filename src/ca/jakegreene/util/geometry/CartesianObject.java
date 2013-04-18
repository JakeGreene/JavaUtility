package ca.jakegreene.util.geometry;

import java.util.List;

interface CartesianObject<D extends Dimension> {
	
	public int size();

	public double get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Create a new CartesianObject based on this CartesianObject but with component <code>index</code>
	 * changed to have the value <code>value</code>
	 * @param index
	 * @param value
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public CartesianObject<D> set(int index, double value) throws IndexOutOfBoundsException;
	
	/**
	 * A View of this CartesianObject's components. Changing the returned list has no effect on this
	 * CartesianObject
	 * @return
	 */
	public List<Double> components();
}
