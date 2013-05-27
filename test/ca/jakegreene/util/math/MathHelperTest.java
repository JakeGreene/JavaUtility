package ca.jakegreene.util.math;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class MathHelperTest {
	
	private static float DELTA = 0.01f;

	@Test
	public void testNormalizeInPlace() {
		int length = 2;
		int width = 2;
		float[][] data = new float[length][width];
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				data[x][y] = x + y;
			}
		}
		float largestValue = (length - 1) + (width - 1);
		float smallestValue = 0;
		
		MathHelper.normalizeInPlace(data);
		
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				float oldValue = (x+y);
				float expected = (oldValue - smallestValue) / (largestValue - smallestValue);
				assertEquals("Normalize In Place. Data normalized", expected, data[x][y], DELTA);
			}
		}
	}
	
	@Test
	public void testNormalizeConstantDataInPlace() {
		int length = 2;
		int width = 2;
		float val = 0.314569f;
		float[][] data = new float[length][width];
		for (int i = 0; i < data.length; ++i) {
			Arrays.fill(data[i], val);
		}
		
		MathHelper.normalizeInPlace(data);
		
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				assertEquals("Normalize Constant Data In Place. All data is 1.0f", 1.0f, data[x][y], DELTA);
			}
		}
		
	}
	
	@Test
	public void testNormalizeData() {
		int length = 2;
		int width = 2;
		float[][] data = new float[length][width];
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				data[x][y] = x + y;
			}
		}
		
		float largestValue = (length - 1) + (width - 1);
		float smallestValue = 0;
		
		float[][] normalized = MathHelper.normalizeData(data); 
		for (int x = 0; x < normalized.length; ++x) {
			for (int y = 0; y < normalized[0].length; ++y) {
				float oldValue = (x+y);
				float expected = (oldValue - smallestValue) / (largestValue - smallestValue);
				assertEquals("Normalize. Data normalized", expected, normalized[x][y], DELTA);
			}
		}
		
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				assertEquals("Normalize. Original Data Unchanged", x+y, data[x][y], DELTA);
			}
		}
	}
	
	@Test
	public void testNormalizeConstantData() {
		int length = 2;
		int width = 2;
		float val = 0.314569f;
		float[][] data = new float[length][width];
		for (int x = 0; x < data.length; ++x) {
			Arrays.fill(data[x], val);
		}
		
		float[][] normalized = MathHelper.normalizeData(data); 
		for (int x = 0; x < normalized.length; ++x) {
			for (int y = 0; y < normalized[0].length; ++y) {
				assertEquals("Normalize Constant Data. Data normalized", 1.0f, normalized[x][y], DELTA);
			}
		}
		
		for (int x = 0; x < data.length; ++x) {
			for (int y = 0; y < data[0].length; ++y) {
				assertEquals("Normalize Constant Data. Original Data Unchanged", val, data[x][y], DELTA);
			}
		}
	}

}
