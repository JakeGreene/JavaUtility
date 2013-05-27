package ca.jakegreene.util.graph;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import ca.jakegreene.util.geometry.Point2D;

public class VoronoiCreater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numPoints = 300;
		int width = 1000;
		int height = 1000;
		Set<Point2D> sites = new HashSet<Point2D>();		
		Map<Point2D, Color> colours = new HashMap<Point2D, Color>();
		while (sites.size() < numPoints) {
			Point2D p1 = Point2D.newPoint(Math.random()*width, Math.random()*height);
			sites.add(p1);
			int grey = (int) (255*sites.size()/(double)(numPoints));
			colours.put(p1, new Color(grey, grey, grey));
		}
		
		BufferedImage voronoi = BruteForceVoronoi.createVoronoi(sites, colours, width, height);
		try {
			ImageIO.write(voronoi, "PNG", new File("/Users/jakegreene/Pictures/voronoi.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
