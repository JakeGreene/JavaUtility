package ca.jakegreene.util.random;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

public class RandomGenerator extends Random {

	private static final long serialVersionUID = -3605698391753745979L;
	private static final MathContext mathContext = MathContext.DECIMAL128;

	public RandomGenerator() {}
	
	public RandomGenerator(long seed) {
		super(seed);
	}
	
	public double nextDouble(double ceiling) {
		return nextDouble() * ceiling;
	}
	
	public double nextDoubleInRange(double floor, double ceiling) {
		// [0.0, 1.0] * (ceiling - floor) + floor
		
		BigDecimal min = new BigDecimal(floor, mathContext); 
		BigDecimal diff = new BigDecimal(ceiling).subtract(new BigDecimal(floor), mathContext);
		BigDecimal result = diff.multiply(new BigDecimal(nextDouble()), mathContext).add(min);
		return result.doubleValue();
	}
	
	public float nextFloat(float ceiling) {
		return nextFloat() * ceiling;
	}
	
	public float nextFloatInRange(float floor, float ceiling) {
		// [0.0, 1.0] * (ceiling - floor) + floor
		
		BigDecimal min = new BigDecimal(floor, mathContext); 
		BigDecimal diff = new BigDecimal(ceiling).subtract(new BigDecimal(floor), mathContext);
		BigDecimal result = diff.multiply(new BigDecimal(nextFloat()), mathContext).add(min);
		return result.floatValue();
	}
	
	public long nextLong(long ceiling) {
		return (long) (nextDouble() * ceiling);
	}
	
	public long nextLongInRange(long floor, long ceiling) {
		// [0.0, 1.0] * (ceiling - floor) + floor
		
		BigDecimal min = new BigDecimal(floor, mathContext); 
		BigDecimal diff = new BigDecimal(ceiling).subtract(new BigDecimal(floor), mathContext);
		BigDecimal result = diff.multiply(new BigDecimal(nextFloat()), mathContext).add(min);
		return result.longValue();
	}
	
	public int nextIntInRange(int floor, int ceiling) {
		// [0.0, 1.0] * (ceiling - floor) + floor
		
		BigDecimal min = new BigDecimal(floor, mathContext); 
		BigDecimal diff = new BigDecimal(ceiling).subtract(new BigDecimal(floor), mathContext);
		BigDecimal result = diff.multiply(new BigDecimal(nextFloat()), mathContext).add(min);
		return result.intValue();
	}
}
