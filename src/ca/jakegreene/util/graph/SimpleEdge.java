package ca.jakegreene.util.graph;


public final class SimpleEdge<V> extends AbstractEdge<V> {
	private SimpleEdge(V source, V destination) {
		super(source, destination);
	}
	
	private SimpleEdge(V source, V destination, double weight) {
		super(source, destination, weight);
	}

	@Override
	public boolean isBidirectional() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (o == null) return false;
		if (o instanceof SimpleEdge) {
			@SuppressWarnings("rawtypes")
			SimpleEdge other = (SimpleEdge) o;
			return equalsBidirectionalEdge(other);
		} else if (o instanceof DirectedEdge) {
			@SuppressWarnings("rawtypes")
			DirectedEdge other = (DirectedEdge) o;
			return other.equals(this);
		}
		return false;
	}
	
	static <V> SimpleEdge<V> newEdge(V source, V destination) {
		return new SimpleEdge<V>(source, destination);
	}
	
	static <V> SimpleEdge<V> newWeightedEdge(V source, V destination, double weight) {
		return new SimpleEdge<V>(source, destination, weight);
	}
	
	public static class Factory<V> implements EdgeFactory<V, SimpleEdge<V>> {

		@Override
		public SimpleEdge<V> createEdge(V source, V destination) {
			return SimpleEdge.newEdge(source, destination);
		}
	}
}
