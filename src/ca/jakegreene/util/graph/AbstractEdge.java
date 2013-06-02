package ca.jakegreene.util.graph;

import com.google.common.base.Objects;

public abstract class AbstractEdge<V> implements Edge<V> {
	
	public static final double DEFAULT_WEIGHT = 1.0;

	private V source;
	private V destination;
	private double weight;
	
	public AbstractEdge(V source, V destination) {
		this(source, destination, DEFAULT_WEIGHT);
	}
	
	public AbstractEdge(V source, V destination, double weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	@Override
	public V source() {
		return source;
	}

	@Override
	public V destination() {
		return destination;
	}

	@Override
	public double weight() {
		return weight;
	}
	
	@SuppressWarnings("rawtypes")
	protected boolean equalsBidirectionalEdge(Edge other) {
		boolean sameSourceAndDest = Objects.equal(source(), other.source()) 
									&& Objects.equal(destination(), other.destination());
		boolean switchedSourceAndDest = Objects.equal(source(), other.destination()) 
									&& Objects.equal(destination(), other.source());
		return  (sameSourceAndDest || switchedSourceAndDest)
				&& Objects.equal(weight(), other.weight());
	}

}
