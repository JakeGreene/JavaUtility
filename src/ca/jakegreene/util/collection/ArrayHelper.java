package ca.jakegreene.util.collection;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayHelper {
	
	private ArrayHelper() {}
	
	/**
	 * Find the smallest element of <code>data</code>
	 * @param data
	 * @return The smallest element of <code>data</code> or <code>null</code> if 
	 * <code>data</code> is empty.
	 */
	public static float min(float[][] data) {
		return min(box(data));
	}
	
	/**
	 * Find the largest element of <code>data</code>
	 * @param data
	 * @return The largest element of <code>data</code> or <code>null</code> if
	 * <code>data</code> is empty
	 */
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
	
	/**
	 * Fill <code>emptyArray</code> with <code>val</code>
	 * @param emptyArray The array to be modified
	 * @param val The value to fill <code>emptyArray</code> with
	 */
	public static float[][] fillInPlace(float[][] emptyArray, float val) {
		for (int i = 0; i < emptyArray.length; ++i) {
			Arrays.fill(emptyArray[i], val);
		}
		return emptyArray;
	}
	
	/**
	 * Fill <code>emptyArray</code> with <code>val</code>
	 * @param emptyArray The array to be modified
	 * @param val The value to fill <code>emptyArray</code> with
	 */
	public static <E> E[][] fillInPlace(E[][] emptyArray, E val) {
		for (int i = 0; i < emptyArray.length; ++i) {
			Arrays.fill(emptyArray[i], val);
		}
		return emptyArray;
	}
	
	/**
	 * Create a new 1D array of type <code>E</code>
	 * with dimension <code>length</code>.
	 * <br />
	 * <br />
	 * <code>E[] data = ArrayHelper.new1DArray(eClass, length); // eClass is of type Class&ltE&gt</code>
	 * <br />
	 * is similar to the following if it were syntactically valid:
	 * <br />
	 * <code>E[] data = new E[length];</code>
	 * @param type
	 * @param length
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[] new1DArray(Class<E> type, int length) {
		return (E[])Array.newInstance(type, length);
	}
	
	/**
	 * Create a new 2D array of type <code>E</code>
	 * with dimensions <code>length</code> and <code>width</code>.
	 * <br />
	 * <br />
	 * <code>E[][] data = ArrayHelper.new2DArray(eClass, length, width); //eClass is of type Class&ltE&gt</code> 
	 * <br />
	 * is similar to the following if it were syntactically valid:
	 * <br />
	 * <code>E[][] data = new E[length][width];</code>
	 * @param type
	 * @param length
	 * @param width
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> E[][] new2DArray(Class<E> type, int length, int width) {
		return (E[][])Array.newInstance(type, length, width);
	}	
}
