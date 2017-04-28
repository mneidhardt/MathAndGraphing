package dk.meem.graphing;

import java.awt.Graphics2D;

public interface Geometry {
	
	public void draw(Graphics2D g);
	public Point firstPoint();
	public void translate(Point p);
	public String toString();

}
