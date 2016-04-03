package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import dk.meem.graphing.primitive.Point;

public class TileElement extends Geometry {
	List<Point> points = new ArrayList<Point>();
	int maxY;
	
	public TileElement(int maxY, int[][] pts) {
		this.maxY = maxY;
		
		points.add(new Point(100,150));
		points.add(new Point(100,200));
		points.add(new Point(100,200));
		points.add(new Point(20,250));
		points.add(new Point(20,250));
		points.add(new Point(100,300));
		points.add(new Point(100,300));
		points.add(new Point(100,350));
	}
	
	public void rotate(double radians, Point pivot) {
		this.translate(pivot.multBy(-1.0));
		
		List<Point> rpoints = new ArrayList<Point>();
		
		for (Point point : this.points) {
			rpoints.add(point.rotate(radians));
		}
		
		this.points = rpoints;
		
		this.translate(pivot);
	}
	
	public void translate(Point diff) {
		List<Point> rpoints = new ArrayList<Point>();
		
		for (Point point : this.points) {
			rpoints.add(point.add(diff));
		}
		
		this.points = rpoints;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		
		for (int i=0; i<points.size()-1; i++) {
			g.drawLine((int)points.get(i).getX(), this.maxY-(int)points.get(i).getY(),
					   (int)points.get(i+1).getX(), this.maxY-(int)points.get(i+1).getY());
			
		}

	}
}
