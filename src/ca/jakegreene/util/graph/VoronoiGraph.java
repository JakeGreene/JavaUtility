package ca.jakegreene.util.graph;

import java.util.Collection;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import ca.jakegreene.util.geometry.Point2D;

public class VoronoiGraph implements UndirectedGraph<Point2D, DefaultEdge> {
	
	UndirectedGraph<Point2D, DefaultEdge> graph;
	
	private VoronoiGraph(Set<Point2D> sites) {
		
	}

	public static VoronoiGraph newVoronoi(Set<Point2D> sites) {
		return new VoronoiGraph(sites);
	}
	
	@Override
	public DefaultEdge addEdge(Point2D arg0, Point2D arg1) {
		return graph.addEdge(arg0, arg1);
	}

	@Override
	public boolean addEdge(Point2D arg0, Point2D arg1, DefaultEdge arg2) {
		return graph.addEdge(arg0, arg1, arg2);
	}

	@Override
	public boolean addVertex(Point2D arg0) {
		return graph.addVertex(arg0);
	}

	@Override
	public boolean containsEdge(DefaultEdge arg0) {
		return graph.containsEdge(arg0);
	}

	@Override
	public boolean containsEdge(Point2D arg0, Point2D arg1) {
		return graph.containsEdge(arg0, arg1);
	}

	@Override
	public boolean containsVertex(Point2D arg0) {
		return graph.containsVertex(arg0);
	}

	@Override
	public Set<DefaultEdge> edgeSet() {
		return graph.edgeSet();
	}

	@Override
	public Set<DefaultEdge> edgesOf(Point2D arg0) {
		return graph.edgesOf(arg0);
	}

	@Override
	public Set<DefaultEdge> getAllEdges(Point2D arg0, Point2D arg1) {
		return graph.getAllEdges(arg0, arg1);
	}

	@Override
	public DefaultEdge getEdge(Point2D arg0, Point2D arg1) {
		return graph.getEdge(arg0, arg1);
	}

	@Override
	public EdgeFactory<Point2D, DefaultEdge> getEdgeFactory() {
		return graph.getEdgeFactory();
	}

	@Override
	public Point2D getEdgeSource(DefaultEdge arg0) {
		return graph.getEdgeSource(arg0);
	}

	@Override
	public Point2D getEdgeTarget(DefaultEdge arg0) {
		return graph.getEdgeTarget(arg0);
	}

	@Override
	public double getEdgeWeight(DefaultEdge arg0) {
		return graph.getEdgeWeight(arg0);
	}

	@Override
	public boolean removeAllEdges(Collection<? extends DefaultEdge> arg0) {
		return graph.removeAllEdges(arg0);
	}

	@Override
	public Set<DefaultEdge> removeAllEdges(Point2D arg0, Point2D arg1) {
		return graph.removeAllEdges(arg0, arg1);
	}

	@Override
	public boolean removeAllVertices(Collection<? extends Point2D> arg0) {
		return graph.removeAllVertices(arg0);
	}

	@Override
	public boolean removeEdge(DefaultEdge arg0) {
		return graph.removeEdge(arg0);
	}

	@Override
	public DefaultEdge removeEdge(Point2D arg0, Point2D arg1) {
		return graph.removeEdge(arg0, arg1);
	}

	@Override
	public boolean removeVertex(Point2D arg0) {
		return graph.removeVertex(arg0);
	}

	@Override
	public Set<Point2D> vertexSet() {
		return graph.vertexSet();
	}

	@Override
	public int degreeOf(Point2D arg0) {
		return graph.degreeOf(arg0);
	}

}
