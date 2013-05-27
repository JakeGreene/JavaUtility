package ca.jakegreene.util.graph;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

import ca.jakegreene.util.geometry.Point2D;

public class BruteForceVoronoi {
	/**
	 * 
	 * @param points
	 * @param colours
	 * @param height
	 * @param width
	 * @return
	 */
	public static BufferedImage createVoronoi(Set<Point2D> sites, Map<Point2D, Color> colours, int height, int width) {
		BufferedImage voronoi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				Point2D point = Point2D.newPoint(w, h);
				double closestDistanceSquared = Double.MAX_VALUE;
				Point2D closestSite = null;
				for (Point2D site : sites) {
					double distance = point.distanceSquared(site);
					if (distance < closestDistanceSquared) {
						closestDistanceSquared = distance;
						closestSite = site;
					}
				}
				voronoi.setRGB(w, h, colours.get(closestSite).getRGB());	
			}
		}
		
		for (Point2D site : sites) {
			drawPoint(site, Color.RED, 2, voronoi);
		}	
		return voronoi;
	}
	
	private static void drawPoint(Point2D point, Color colour, int radius, BufferedImage image) {
		int centreX = (int) point.getX();
		int centreY = (int) point.getY();
		int startingX = Math.max(0, centreX - radius);
		int endingX = Math.min(image.getWidth() - 1, centreX + radius);
		int startingY = Math.max(0, centreY - radius);
		int endingY = Math.min(image.getHeight() - 1, centreY + radius);
		for (int x = startingX; x <= endingX; ++x) {
			for (int y = startingY; y <= endingY; ++y) {
				image.setRGB(x, y, colour.getRGB());
			}
		}
	}
}
