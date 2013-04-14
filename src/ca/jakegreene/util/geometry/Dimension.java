package ca.jakegreene.util.geometry;

import java.util.List;

public interface Dimension<D extends Dimension<D>> {
	/**
	 * Determine the size of this Dimension.
	 * e.g. A 2-Dimensional Dimension will return 2
	 * @return The size
	 */
	public int size();
	
	/**
	 * Get the component at <code>index</code>. Components
	 * are 0-indexed
	 * @param index valid between 0 and size() - 1
	 * @return The component at <code>index</code>
	 */
	public double getComponent(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Create a Dimension instance of the same dimensionality with the given components
	 * @param components
	 * @return
	 */
	public D create(List<Double> components);
}
