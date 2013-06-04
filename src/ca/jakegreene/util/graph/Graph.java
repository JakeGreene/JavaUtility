package ca.jakegreene.util.graph;

import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;

public interface Graph<V, E extends Edge<V>> {
	public boolean containsVertex(V vertex);
	public boolean containsEdge(E edge);
	public boolean containsEdge(V source, V destination);
	public Optional<E> getEdge(V source, V destination);
	public List<E> getEdges(V vertex);
	public Set<V> getNeighours(V vertex);
	public Set<V> vertices();
	public List<E> edges();
}
