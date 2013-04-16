package ca.jakegreene.util.geometry;

public class VectorD3Test extends VectorTest<D3> {
	
	public VectorD3Test() {
		super(new GeneralVector<D3>(new D3(-0.0, -0.0005, -897.001)), 0.1);
	}

	@Override
	protected Vector<D3> createTestVector() {
		return new GeneralVector<D3>(new D3(909090.0, 1234.567, 0.098));
	}

}
