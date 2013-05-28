package ca.jakegreene.util.math;

import ca.jakegreene.util.collection.ArrayHelper;

public class MathHelper {
	private MathHelper() {}
	
	/**
	 * Determine if <code>num</code> is a power
	 * of 2
	 * @param num
	 * @return
	 */
	public static boolean isPowerOfTwo(int num) {
		/*
		 * For a number n = 2^m for some m
		 * the bits representing n will have a single
		 * 1 followed by 0s. n - 1 will then flip this
		 * arrangement so that where there was a 1 there
		 * is now a 0 followed by 1s. n & (n-1) = 0
		 */
		return (num & (num-1)) == 0;
	}
	
	/**
	 * Normalize the data. This implies that the new
	 * data will be in the range [0, 1] and each entry
	 * new_i = new_j iff data_i = data_j for all i,j
	 * @param data The data to normalize
	 * @return normalized data
	 */
	public static float[][] normalizeData(float[][] data) {
		float[][] normalized = data.clone();	
		normalizeInPlace(normalized);
		return normalized;
	}
	
	/**
	 * Normalize the data in place. This implies that the new
	 * data will be in the range [0, 1] and each entry
	 * new_i = new_j iff old_i = old_j for all i,j
	 * @param data The data to normalize and the array to store the results in
	 */
	public static void normalizeInPlace(float[][] data) {
		float lowest = ArrayHelper.min(data);
		float highest = ArrayHelper.max(data);
		
		if (highest == lowest) {
			ArrayHelper.fillInPlace(data, 1.0f);
		} else {
			for (int x = 0; x < data.length; ++x) {
				for (int y = 0; y < data[0].length; ++y) {
					// FIXME This does not work for very large 'highest' with very low 'lowest'
					data[x][y] = (data[x][y] - lowest) / (highest - lowest);
				}
			}
		}		
	}
}
