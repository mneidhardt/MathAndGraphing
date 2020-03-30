package dk.meem.graphing;

import java.util.ArrayList;

public class EquidistantPolygon extends Polygon {	
	public EquidistantPolygon() {
	}
	
	public EquidistantPolygon(Point center) {
		this.polygon = this.getPoints();
	}
	
	public ArrayList<Point> getPoints() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 200));
		points.add(new Point(100,100));
		
		return points;
	}
	
}
