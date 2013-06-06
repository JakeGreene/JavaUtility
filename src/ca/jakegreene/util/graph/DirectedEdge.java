package ca.jakegreene.util.graph;

import com.google.common.base.Objects;
import com.google.common.primitives.Doubles;

public final class DirectedEdge<V> extends AbstractEdge<V> {
	
	private final boolean bidirectional;

	private DirectedEdge(V source, V destination, boolean bidirectional) {
		super(source, destination);
		this.bidirectional = bidirectional;
	}
	
	private DirectedEdge(V source, V destination, double weight, boolean bidirectional) {
		super(source, destination, weight);
		this.bidirectional = bidirectional;
	}

	@Override
	public boolean isBidirectional() {
		return bidirectional;
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
			if (this.isBidirectional() && other.isBidirectional()) {
				return equalsBidirectionalEdge(other);
			} else {
				// One could be bidirectional and the other could be directed
				return Objects.equal(source(), other.source()) 
						&& Objects.equal(destination(), other.destination())
						&& Objects.equal(weight(), other.weight())
						&& Objects.equal(isBidirectional(), other.isBidirectional());
			}
			
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if (bidirectional) {
			return source().hashCode() + destination().hashCode() + Doubles.hashCode(weight());
		} else {
			return Objects.hashCode(source(), destination(), weight());
		}
	}
	
	static <V> DirectedEdge<V> newEdge(V source, V destination) {
		return new DirectedEdge<V>(source, destination, false);
	}
	
	static <V> DirectedEdge<V> newWeightedEdge(V source, V destination, double weight) {
		return new DirectedEdge<V>(source, destination, weight, false);
	}
	
	static <V> DirectedEdge<V> newBidirectionalEdge(V source, V destination) {
		return new DirectedEdge<V>(source, destination, true);
	}
	
	static <V> DirectedEdge<V> newWeightedBidirectionalEdge(V source, V destination, double weight) {
		return new DirectedEdge<V>(source, destination, true);
	}
	
	public static class Factory<V> implements EdgeFactory<V, DirectedEdge<V>> {

		@Override
		public DirectedEdge<V> createEdge(V source, V destination) {
			return DirectedEdge.newEdge(source, destination);
		}

		@Override
		public DirectedEdge<V> createEdge(V source, V destination, double weight) {
			return DirectedEdge.newWeightedEdge(source, destination, weight);
		}

		@Override
		public DirectedEdge<V> createBidirectionalEdge(V source, V destination) {
			return DirectedEdge.newBidirectionalEdge(source, destination);
		}

		@Override
		public DirectedEdge<V> createBidirectionalEdge(V source, V destination,
				double weight) {
			return DirectedEdge.newWeightedBidirectionalEdge(source, destination, weight);
		}
	}
}
