package ca.jakegreene.util.math;

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
		 * The rational is as follows:
		 * For a number n = 2^m for some m
		 * the bits representing n will have a single
		 * 1 followed by 0s. n - 1 will then flip this
		 * arrangement so that where there was a 1 there
		 * is now a 0 followed by 1s. n & (n-1) = 0
		 */
		return (num & (num-1)) == 0;
	}
}
