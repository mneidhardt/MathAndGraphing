package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Polygon implements Geometry {
	private Color graphColor = new Color(0,0,0);
	private ArrayList<Point> polygon = new ArrayList<Point>();
	
	public Polygon() {
	}
	
	public Polygon(ArrayList<Point> plist) {
		this.polygon = plist; 
	}
	
	public void clear() {
		this.polygon.clear();
	}
	
	public int size() {
		return polygon.size();
	}
	
	public boolean isEmpty() {
		return polygon.isEmpty();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(graphColor);
		
		//Point centro = this.centroid();

		for (int i=0; i<polygon.size(); i++) {
			int j = (i+1) % polygon.size();

			g.drawLine((int)polygon.get(i).getX(), (int)polygon.get(i).getY(),
					   (int)polygon.get(j).getX(), (int)polygon.get(j).getY());
		}
		
		//g.drawArc((int)centro.getX(), (int)centro.getY(), 6, 6, 0, 360);
	}
	
	public Point firstPoint() {
		if ( ! polygon.isEmpty()) {
			return polygon.get(0);
		} else {
			return null;
		}
	}

	/* This moves all polygons, by translating them all 
	 * by the first point in first polygon.
	 */
	public void toOrigo() {
		Point base = this.firstPoint();
		this.translate(base.multBy(-1.0));
	}

	public void translate(Point t) {
		if (polygon.isEmpty()) {
			return;
		} else {
			for (int i = 0; i<polygon.size(); i++) {
				polygon.set(i, polygon.get(i).add(t));
			}
		}
	}
	
	public void add(Point p) {
		polygon.add(p);
	}
	
	public Point centroid() {
		double cx=0.0, cy=0.0;
		
		for (int i=0; i<this.polygon.size(); i++) {
			int j = (i+1) % this.polygon.size();
			
			cx += (this.polygon.get(i).getX() + this.polygon.get(j).getX()) *
			      (this.polygon.get(i).getX() * this.polygon.get(j).getY() -
			    	this.polygon.get(j).getX() * this.polygon.get(i).getY());
			
			cy += (this.polygon.get(i).getY() + this.polygon.get(j).getY()) *
				  (this.polygon.get(i).getX() * this.polygon.get(j).getY() -
				   this.polygon.get(j).getX() * this.polygon.get(i).getY());
		}

		double area = this.area();
		return new Point(Math.abs(cx/(6*area)), Math.abs(cy/(6*area)));
	}
	
	public double area() {
		double result = 0.0;
		
		for (int i=0; i<this.polygon.size(); i++) {
			int j = (i+1) % this.polygon.size();
			
			result += this.polygon.get(i).getX() * this.polygon.get(j).getY();
			result -= this.polygon.get(j).getX() * this.polygon.get(i).getY();
		}
		
		return Math.abs(result/2.0);
	}

	@Override
	public String toString() {
		String res = "Polygon. polygon=" + System.lineSeparator();
		for (Point p : this.polygon) {
			res += p.toString() + System.lineSeparator();
		}
		
		return res;
	}

}
