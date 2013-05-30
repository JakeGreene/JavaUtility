package ca.jakegreene.util.random;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomGeneratorTest {
	
	RandomGenerator gen = new RandomGenerator();

	@Test
	public void testSameSeed() {
		long seed = 5279; // Prime
		RandomGenerator seeded = new RandomGenerator(seed);
		RandomGenerator sameSeeded = new RandomGenerator(seed);
		
		for (int i = 0; i < 100; ++i) {
			assertEquals("RandomGenerator Same Seed => Same Value", seeded.nextInt(), sameSeeded.nextInt());	
		}
	}
	
	@Test
	public void testDoubleInRangeMax() {
		// Test if there is overflow
		double randDouble = gen.nextDoubleInRange(-Double.MAX_VALUE, Double.MAX_VALUE);
		assertTrue("RandomGenerator Double in Range. Test Max Range", randDouble != Double.NaN);
		assertTrue("RandomGenerator Double in Range. Test Max Range", randDouble != Double.NEGATIVE_INFINITY); 
		assertTrue("RandomGenerator Double in Range. Test Max Range", randDouble != Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void testFloatInRangeMax() {
		// Test if there is overflow
		float randFloat = gen.nextFloatInRange(-Float.MAX_VALUE, Float.MAX_VALUE);
		assertTrue("RandomGenerator Float in Range. Test Max Range: Not NaN", randFloat != Float.NaN);
		assertTrue("RandomGenerator Float in Range. Test Max Range: Not -Inf", randFloat != Float.NEGATIVE_INFINITY);
		assertTrue("RandomGenerator Float in Range. Test Max Range: Not +Inf", randFloat != Float.POSITIVE_INFINITY);
	}
	
	@Test
	public void testIntInRangeMax() {
		// Test if there is overflow
		int randInt = gen.nextIntInRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
		assertTrue("RandomGenerator Int in Range. Test Max Range", randInt <= Integer.MAX_VALUE && randInt >= Integer.MIN_VALUE);
	}
	
	@Test
	public void testIntInRangeOverflow() {
		// Test if there is overflow
		int lowerBound = -1;
		int randInt = gen.nextIntInRange(lowerBound, Integer.MAX_VALUE);
		assertTrue("RandomGenerator Int in Range. Test Overflow-Inducing Range", randInt <= Integer.MAX_VALUE && randInt >= lowerBound);
	}
	
	@Test
	public void testInInRangeUnderflow() {
		// Test if there is underflow
		int upperBound = 1;
		int randInt = gen.nextIntInRange(Integer.MIN_VALUE, upperBound);
		assertTrue("RandomGenerator Int in Range. Test Underflow-Inducing Range", randInt <= upperBound && randInt >= Integer.MIN_VALUE);		
	}
	
	@Test
	public void testLongInRangeMax() {
		// Test if there is overflow
		long randLong = gen.nextLongInRange(Long.MIN_VALUE, Long.MAX_VALUE);
		assertTrue("RandomGenerator Long in Range. Test Max Range", randLong <= Long.MAX_VALUE && randLong >= Long.MIN_VALUE);
	}
	
	@Test
	public void testLongInRangeOverflow() {
		// Test if there is overflow
		long lowerBound = -1;
		long randLong = gen.nextLongInRange(lowerBound, Long.MAX_VALUE);
		assertTrue("RandomGenerator Long in Range. Test Overflow-Inducing Range", randLong <= Long.MAX_VALUE && randLong >= lowerBound);
	}

	@Test
	public void testLongInRangeUnderflow() {
		// Test if there is underflow
		long upperBound = 1;
		long randLong = gen.nextLongInRange(Long.MIN_VALUE, upperBound);
		assertTrue("RandomGenerator Long in Range. Test Underflow-Inducing Range", randLong <= upperBound && randLong >= Long.MIN_VALUE);
	}
}
