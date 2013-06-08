package ca.jakegreene.util.graph;

import java.util.List;
import java.util.Set;

/**
 * A Graph is a representation of a set of objects where some pairs of the objects 
 * are connected by links. The interconnected objects are represented by mathematical 
 * abstractions called vertices, and the links that connect some pairs of vertices 
 * are called edges.
 * @author jakegreene
 *
 * @param <V> The Vertex type
 * @param <E> The Edge type
 */
public interface Graph<V, E extends Edge<V>> {
	
	/**
	 * Does this graph contain <code>vertex</code>.
	 * This will return true iff <code>vertex.equal(candidate)</code>
	 * is true for some candidate within this Graph 
	 * @param vertex
	 * @return
	 */
	public boolean containsVertex(V vertex);
	
	/**
	 * Does this graph contain <code>edge</code>.
	 * This will return true iff <code>edge.equal(candidate)</code>
	 * is true for some candidate within this Graph 
	 * @param edge
	 * @return
	 */
	public boolean containsEdge(E edge);
	
	/**
	 * Does this graph contain an edge from <code>source</code>
	 * to <code>destination</code>.
	 * 
	 * Note: if a bidirectional edge (destination, source) exists, this method
	 * will return true
	 * @param source
	 * @param destination
	 * @return
	 */
	public boolean containsEdge(V source, V destination);
	
	/**
	 * Get all of the edges that allow travel from <code>vertex</code>.
	 * 
	 * Note: if only a directed edge (other, vertex) exists, this method will
	 * return false
	 * @param vertex
	 * @return
	 */
	public List<E> getEdges(V vertex);
	
	/**
	 * Get all of the vertices that can be immediately reached
	 * by <code>vertex</code>
	 * @param vertex
	 * @return
	 */
	public Set<V> getNeighours(V vertex);
	
	/**
	 * Get all of the vertices within this Graph
	 * @return Set of vertices. Changes to this set will not change this Graph
	 */
	public Set<V> vertices();
	
	/**
	 * Get all of the edges within this Graph
	 * @return List of edges. Changes to this list will not change this Graph
	 */
	public List<E> edges();
}
