package ca.jakegreene.util.graph;

/**
 * An Edge is a connection between two vertices within a Graph.
 * All edges have a weight: a value assigned to them. The meaning
 * of this weight is algorithm specific but is normally interpreted
 * as the cost of moving from the source to the destination.
 * @author jakegreene
 *
 * @param <V>
 */
public interface Edge<V> {
	/**
	 * The source (start) of the edge
	 * @return
	 */
	V source();
	
	/**
	 * The destination (end-point) of the edge
	 * @return
	 */
	V destination();
	
	/**
	 * The weight assigned to an edge
	 * @return
	 */
	double weight();
	
	/**
	 * Is movement allowed:
	 * <br />
	 * <br />
	 * 1) From the source to the destination?
	 * <br />
	 * AND
	 * <br />
	 * 2) From the destination to the source?
	 * @return
	 */
	boolean isBidirectional();
}
