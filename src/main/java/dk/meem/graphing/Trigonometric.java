package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;

import dk.meem.graphing.primitive.Point;

public class Trigonometric extends Mathfunction {
	double frequency;
	double amplitude;
	Color graphColor;
	
	public Trigonometric() {
		this.frequency = 0.05;
		this.amplitude = 60.0;
		this.graphColor = new Color(50,75,200);
	}
	
    public void draw(Graphics2D g) {
        super.draw(g,  1,  1000,  1, graphColor);
    }

    /* Evaluates point on ellipsis at time t.
     */
    @Override public Point evaluate(double x) {
        return new Point(x, Math.round(amplitude*Math.sin(frequency*x))-50);
    }
    
}
