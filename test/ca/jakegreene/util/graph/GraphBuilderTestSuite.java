package ca.jakegreene.util.graph;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DirectedGraphBuilderTest.class, SimpleGraphBuilderTest.class,
		WeightedGraphBuilderTest.class })
public class GraphBuilderTestSuite {
	public static void main(String [] args) {
		Result result = JUnitCore.runClasses(GraphBuilderTestSuite.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
