package ca.jakegreene.util.collection;

public class ArrayHelper {
	
	public static float min(float[][] data) {
		return min(box(data));
	}
	
	public static float max(float[][] data) {
		return max(box(data));
	}
	
	/**
	 * Find the smallest element of <code>data</code>
	 * @param data
	 * @return The smallest element of <code>data</code> or <code>null</code> if 
	 * <code>data</code> is empty.
	 */
	public static <E extends Comparable<E>> E min(E[][] data) {
		E smallest = null;
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				E current = data[x][y];
				if (current != null && (smallest == null || current.compareTo(smallest) < 0)) {
					smallest = data[x][y];
				}
			}
		}
		return smallest;
	}
	
	/**
	 * Find the largest element of <code>data</code>
	 * @param data
	 * @return The largest element of <code>data</code> or <code>null</code> if
	 * <code>data</code> is empty
	 */
	public static <E extends Comparable<E>> E max(E[][] data) {
		E largest = null;
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				E current = data[x][y];
				if (current != null && (largest == null || current.compareTo(largest) > 0)) {
					largest = data[x][y];
				}
			}
		}
		return largest;
	}
	
	/**
	 * Convert <code>data</code> to a 2D Float array 
	 * @param data
	 * @return The values within <code>data</code> converted into
	 * Floats or an empty array if <code>data</code> is empty.
	 */
	public static Float[][] box(float[][] data) {
		Float[][] boxedData = new Float[data.length][data[0].length];
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				boxedData[x][y] = data[x][y];
			}
		}
		return boxedData;
	}
	
	/**
	 * Convert <code>data</code> to a 2D float array
	 * @param data
	 * @return The values within <code>data</code> converted into
	 * floats or an empty array if <code>data<code> is empty.
	 */
	public static float[][] unbox(Float[][] data) {
		float[][] boxedData = new float[data.length][data[0].length];
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				boxedData[x][y] = data[x][y];
			}
		}
		return boxedData;
	}
}
