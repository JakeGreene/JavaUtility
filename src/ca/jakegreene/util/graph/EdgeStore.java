package ca.jakegreene.util.graph;

import java.util.Collection;

public interface EdgeStore<V, E extends Edge<V>> {
	/**
	 * Add this edge to the edge store. This action
	 * may replace a previous edge depending on the
	 * implementation.
	 * @param edge
	 */
	public void addEdge(E edge);
	
	/**
	 * Remove all edges equal to this edge
	 * @param edge
	 */
	public void removeEdge(E edge);
	
	/**
	 * Remove all the edges in this EdgeStore that
	 * allow travel from source to destination
	 * @param source
	 * @param destination
	 */
	public void removeEdges(V source, V destination);
	
	/**
	 * Does this EdgeStore contain an edge equal to
	 * <code>edge</code>
	 * @param edge
	 * @return
	 */
	public boolean contains(E edge);
	
	/**
	 * Does this EdgeStore contain an edge from
	 * <code>source</code> to <code>destination</code>
	 * @param source
	 * @param destination
	 * @return
	 */
	public boolean contains(V source, V destination);
	
	/**
	 * Get all of the edges that allow travel from <code>source</code> 
	 * to <code>destination</code>
	 * @param source
	 * @param destination
	 * @return
	 */
	public Collection<E> getEdges(V source, V destination);
	
	/**
	 * Get all of the edges in this EdgeStore
	 * @return
	 */
	public Collection<E> getEdges();
}
