package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;

import dk.meem.graphing.primitive.Point;

public class Polynomial extends Mathfunction {
	Color graphColor;
	
	public Polynomial(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY-80;
		this.graphColor = new Color(50,75,200);
	}
	
    public void draw(Graphics2D g) {
        super.draw(g, 0, maxX, 1, graphColor);
    }

    /* Evaluates point on ellipsis at time t.
     */
    @Override public Point evaluate(double x) {
    	double realx = x;
    	x -= maxX/2;
    	
    	return new Point(realx, Math.round(0.005*Math.pow(x, 3) + 0.05*Math.pow(x, 2) + 0.005*x));
    	//return new Point(realx, Math.round(0.005*Math.pow(x, 2)));
        //return new Point(realx, 45*Math.round(Math.pow(x, 3)/Math.pow(2, x)));
        //return new Point(realx, Math.round(0.005*Math.pow(x, 3) - 0.005*Math.pow(x, 2) - 3*x + 320));
        //return new Point(realx, 250*Math.round(Math.sin(x)/(x)));
    }
    
}
