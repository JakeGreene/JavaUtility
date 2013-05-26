package ca.jakegreene.util.random;

import java.util.Random;

import ca.jakegreene.util.math.MathHelper;

public class DiamondSquareGenerator {
	
	private final Random gen;
	
	DiamondSquareGenerator() {
		gen = new Random();
	}
	
	DiamondSquareGenerator(int seed) {
		gen = new Random(seed);
	}
	
	/**
	 * Create a noise map with size (width x width) 
	 * @param width Must be equal to (2^n) + 1 for some n > 0
	 * @return a (width x width) array of floats in the range [0, 1]
	 */
	float[][] createNoiseMap(int width) throws IllegalArgumentException {
		if (width <= 0) {
			throw new IllegalArgumentException("Width must be larger than 0");
		}
		if (!MathHelper.isPowerOfTwo(width-1)) {
			throw new IllegalArgumentException("Width must be equal to 2^n + 1 for some positive n");
		}
		
		float[][] noise = new float[width][width];
		
		/*
		 * The methods within DiamondSquareGenerator
		 * modify the data directly instead of returning 
		 * a new 2D array of data. This goes against common
		 * (and my preferred) coding styles but is necessary given 
		 * the potential size of the noise array.
		 */
		
		seedMap(noise);
		
		/*
		 * Iteratively apply the diamond-square algorithm.
		 * Doing this iteratively allows us to wrap when 
		 * calculating the diamond averages during the 
		 * square step because the diamond step will
		 * have been performed for all subsections
		 * with width 'currentWidth'
		 */
		int currentWidth = width;
		while (currentWidth > 2) {	
			applyDiamond(noise, currentWidth);
			applySquare(noise, currentWidth);		
			/*
			 *  Halve the width. This rounds-up the
			 *  halved width in order to get the correct
			 *  size (eg. The half width of 5 is 3 because
			 *  the data point at index 2 is used twice)
			 *  
			 *  x x y z z
			 *  x x y z z
			 *  y y y y y
			 *  a a y b b
			 *  a a y b b
			 */
			currentWidth = (currentWidth/2) + 1;
		}
		normalizeData(noise);
		return noise;
	}
	
	/**
	 * Create data to be used at the start of the 
	 * noise generating process 
	 */
	private void seedMap(float[][] data) {
		int width = data.length;
		// Seed the map with random data at the far corners
		data[0][0] = weightedRandom(width);
		data[0][width-1] = weightedRandom(width);
		data[width-1][0] = weightedRandom(width);
		data[width-1][width-1] = weightedRandom(width);
	}
	
	/**
	 * Determine the value of the centre points of each (width x width)
	 * subsection by averaging the values of the surrounding corners
	 * 
	 * eg. section with width 5
	 * x . . . x
	 * . . . . .
	 * . . o . .
	 * . . . . .
	 * x . . . x
	 * 
	 * eg. iterative application to a 5x5 map with section width 3
	 * will find 4 values (the o's) using a total of 9 "corners" (the x's)
	 * x . x . x
	 * . o . o .
	 * x . x . x
	 * . o . o .
	 * x . x . x
	 */
	private void applyDiamond(float[][] noise, int width) {
		// Perform the 'Diamond' step for each block
		for (int x = 0; x < noise.length - 1; x += (width - 1)) {
			for (int y = 0; y < noise[0].length - 1; y += (width - 1)) {
				int lastX = x + width - 1;
				int lastY = y + width - 1;

				int midX = (x + lastX) / 2;
				int midY = (y + lastY) / 2;

				/* 
				 * "Diamond" step. Set the middle of this block
				 * to the average of the corners of this block
				 * and add some randomness weighted with the current
				 * width.
				 */
				noise[midX][midY] = (noise[x][y] + noise[x][lastY]
						+ noise[lastX][y] + noise[lastX][lastY]) / 4;
				noise[midX][midY] = noise[midX][midY] + weightedRandom(width);
			}
		}
	}
	
	/**
	 * Determine the edge values of each (width x width) section by finding 
	 * the diamond averages around each edge point. This fills in the holes
	 * left by the diamond step.
	 * 
	 * eg. section with width 5
	 * . . x . .
	 * . . . . .
	 * x . o . x
	 * . . . . .
	 * . . x . .
	 * 
	 * eg. edge section with width 5.
	 * The value to be found is on an edge so it will use
	 * the reference value on the other side of the map. In this case,
	 * the reference value X is being used twice.
	 * x . . . .
	 * . . . . .
	 * o . X . .
	 * . . . . .
	 * x . . . .
	 * 
	 * eg iterative application to a 5x5 map with section width 3.
	 * 12 values are determined (the o's) using 13 "corners" (the x's)
	 * provided by the diamond step.
	 * Some of the corners are used multiple times because of wrapping.
	 * x o x o x
	 * o x o x o
	 * x o x o x
	 * o x o x o
	 * x o x o x
	 */
	private void applySquare(float[][] noise, int width) {
		// Perform the 'Square' step for each block
		for (int firstX = 0; firstX < noise.length - 1; firstX += (width - 1)) {
			for (int firstY = 0; firstY < noise[0].length - 1; firstY += (width - 1)) {
				int lastX = firstX + width - 1;
				int lastY = firstY + width - 1;

				int midX = (firstX + lastX) / 2;
				int midY = (firstY + lastY) / 2;

				noise[firstX][midY] = diamondAverage(noise, firstX, midY, width) + weightedRandom(width);
				noise[midX][firstY] = diamondAverage(noise, midX, firstY, width) + weightedRandom(width);
				noise[lastX][midY] = diamondAverage(noise, lastX, midY, width) + weightedRandom(width);
				noise[midX][lastY] = diamondAverage(noise, midX, lastY, width) + weightedRandom(width);
			}
		}
	}
	
	/**
	 * Find the average value of the corners of the diamond surrounding (x, y)
	 * 
	 * . x .
	 * x o x
	 * . x .
	 * 
	 * Where o is the point (x, y) and each x is a point <code>(width-1)/2</code> distance
	 * from o.
	 */
	private float diamondAverage(float[][] noise, int x, int y, int width) {
		int delta = (width - 1) / 2;
		
		/* 
		 * There may not be a mid X/Z to look at
		 * if we are on the edge of the height map.
		 * In this case we will "wrap" and consider
		 * the other side of the grid. This wrap will
		 * be slightly offset in order to use a known 
		 * reference value (a proper wrap would try to
		 * use a yet-to-be-calculated value)
		 * 
		 * A "proper" wrap. Note how the wrapped value (w)
		 * is on the edge and is therefore not yet calculated
		 * x . . . .
		 * o x . . w
		 * x . . . .
		 * . . . . .
		 * . . . . .
		 * 
		 * The wrap used by this method. The wrapped value
		 * (w) is a centre value in a previous step and has
		 * therefore already been calculated
		 * 
		 * x . . . .
		 * o x . w .
		 * x . . . .
		 * . . . . .
		 * . . . . .
		 */
		
		int midLeftX = x - delta;
		if (x == 0) {
			// Use the right hand side
			midLeftX = noise.length - 1 - delta;
		}
			
		int midRightX = x + delta;
		if (x == noise.length - 1) {
			// Use the left hand side
			midRightX = delta;
		}
		
		int midTopY = y - delta;
		if (y == 0) {
			// Use the bottom
			midTopY = noise[0].length - 1 - delta;
		}
		
		int midBottomY = y + delta;
		if (y == noise[0].length - 1) {
			// Use the top
			midBottomY = delta;
		}
		
		float average = (noise[x][midTopY] + noise[midRightX][y] + noise[x][midBottomY] + noise[midLeftX][y]) / 4;
		return average;
	}
	
	/**
	 * Create a pseudo-random number in the range 
	 * (-weight/2, weight/2]
	 */
	private float weightedRandom(int weight) {
		return (float)(gen.nextFloat() * (-weight) + (weight/2.0));
	}
	
	/**
	 * Convert the values of the given noise map
	 * so that they are in the range [0, 1]
	 */
	private void normalizeData(float[][] noise) {
		float lowest = Float.MAX_VALUE;
		float highest = Float.MIN_VALUE;
		for (int x = 0; x < noise.length; ++x) {
			for (int y = 0; y < noise[0].length; ++y) {
				if (noise[x][y] < lowest) {
					lowest = noise[x][y];
				}
				if (noise[x][y] > highest) {
					highest = noise[x][y];
				}
			}
		}
		
		for (int x = 0; x < noise.length; ++x) {
			for (int y = 0; y < noise[0].length; ++y) {
				noise[x][y] = (noise[x][y] - lowest) / (highest - lowest);
			}
		}
		
	}
}
