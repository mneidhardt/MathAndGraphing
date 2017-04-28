package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Mathfunction {
	double translateX;
	double translateY;
	Color graphColor;
	double majoraxis;
	double minoraxis;
	double r= 2.0/2.0;
	
	public Ellipse(double ma, double mi, double transx, double transy) {
		this.translateX = transx;
		this.translateY = transy;
		this.graphColor = new Color(50,75,200);
		this.majoraxis = ma;
		this.minoraxis = mi;
	}
	
    public void draw(Graphics2D g) {
    	super.draw(g,  0,  Math.PI*2,  0.001, graphColor);
    }

    /* Evaluates point on ellipsis at time t.
     */
    @Override public Point evaluate(double t) {
        Point p = new Point(
    			Math.round(majoraxis*Math.pow(Math.cos(t), r)) + translateX,
    			Math.round(minoraxis*Math.pow(Math.sin(t), r)) + translateY
    			);
        
        return p;
    }

}
