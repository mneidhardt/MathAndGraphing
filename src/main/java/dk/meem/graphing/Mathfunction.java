package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import dk.meem.graphing.primitive.Point;

abstract class Mathfunction {
	
	static final float PI = 3.14159265358979323846f;
	public int maxX;
	public int maxY;
	
	public abstract Point evaluate(double x);
	
    public double derivative(double x) {
    	double h = Math.pow(2,  -1);
    	Point p1 = this.evaluate(x-h);
    	Point p2 = this.evaluate(x+h);
    	double diff = (p2.getY() - p1.getY());
    	
    	return diff;
    }

	public void draw(Graphics2D g, double start, double end, double step, Color graphColor) {
			g.setColor(graphColor);

		for (double x = start; x <= end; x += step) {
			Point p0 = this.evaluate(x - step);
			Point p1 = this.evaluate(x);
			g.drawLine((int) p0.getX(), maxY - (int) p0.getY(), (int) p1.getX(), maxY - (int) p1.getY());

			/*
			 * double diff = this.derivative(x);
			 * 
			 * if (diff < 0) { g.setColor(new Color(255,0,0)); } else if (diff
			 * == 0) { g.setColor(new Color(255,255,255)); } else {
			 * g.setColor(new Color(0,0,255)); } g.drawLine((int)x, 50, (int)x,
			 * 50+3*(int)Math.abs(Math.round(diff)));
			 */
		}
	}
}
