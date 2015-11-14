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
        super.draw(g,  1,  74, 1, graphColor);
    }

    /* Evaluates point on ellipsis at time t.
     */
    @Override public Point evaluate(double x) {
    	//return new Point(x, Math.round(0.005*Math.pow(x, 2)));
        return new Point(x, 45*Math.round(Math.pow(x, 3)/Math.pow(2, x)));
        //return new Point(x, Math.round(0.005*Math.pow(x, 3) - 0.005*Math.pow(x, 2) - 3*x + 320));
    }
    
}
