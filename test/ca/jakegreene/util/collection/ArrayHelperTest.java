package ca.jakegreene.util.collection;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayHelperTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testString2DMin() {
		String[][] data = new String[][]{ {"Zoo", "Yellow", "Wombat"}, {"Alpha", "Beta", "Gamma"}, {"Money", "No", "Problem"} };
		String min = ArrayHelper.min(data);
		assertEquals("ArrayHelper String 2D. Min", "Alpha", min);
	}
	
	@Test
	public void testNull2DMin() {
		String[][] data = new String[][]{ {null, null}, {null, null} };
		String min = ArrayHelper.min(data);
		assertEquals("ArrayHelper Null 2D. Min", null, min);
	}
	
	@Test
	public void testString2DMax() {
		String[][] data = new String[][]{ {"Alpha", "Beta", "Gamma"}, {"Money", "No", "Problem"}, {"Zoo", "Yellow", "Wombat"} };
		String max = ArrayHelper.max(data);
		assertEquals("ArrayHelper String 2D. Max", "Zoo", max);
	}
	
	@Test
	public void testNull2DMax() {
		String[][] data = new String[][]{ {null, null}, {null, null} };
		String max = ArrayHelper.max(data);
		assertEquals("ArrayHelper Null 2D. Min", null, max);
	}
	
	

}
