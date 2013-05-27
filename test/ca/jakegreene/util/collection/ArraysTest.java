package ca.jakegreene.util.collection;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArraysTest {
	
	private float[][] floatArray;

	@Before
	public void setUp() throws Exception {
		floatArray = new float[][]{ {0.0f, 0.5f, 1.0f}, {-0.1f, 0.1f, 0.0f}, {Float.MAX_VALUE, Float.MIN_VALUE, 0.0f} };
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
