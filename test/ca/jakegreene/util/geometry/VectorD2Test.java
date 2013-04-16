package ca.jakegreene.util.geometry;

public class VectorD2Test extends VectorTest<D2> {
	
	public VectorD2Test() {
		super(new GeneralVector<D2>(new D2(345.8, -9990.0)), 0.1);
	}

	@Override
	protected Vector<D2> createTestVector() {
		return new GeneralVector<D2>(new D2(0.0, 77.7)); 
	}

}
