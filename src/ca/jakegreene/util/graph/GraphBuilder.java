package ca.jakegreene.util.graph;

import java.util.Iterator;

public interface GraphBuilder<V, E extends Edge<V>> {
	public void addVertex(V vertex);
	public void addVertices(Iterable<V> vertices);
	public void addVertices(Iterator<V> vertices);
	public void addEdge(V source, V destination);
}
