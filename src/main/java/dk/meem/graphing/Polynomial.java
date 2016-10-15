package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import dk.meem.graphing.primitive.Point;

public class Polynomial extends Mathfunction {
	Color graphColor;
	double[] coefficients;
	
	public Polynomial(int maxX, int maxY, double[] coefficients) {
		this.maxX = maxX;
		this.maxY = maxY-80;
		this.graphColor = new Color(50,75,200);
		
		this.coefficients = coefficients;
	}
		
    public void draw(Graphics2D g) {
        super.draw(g, 0, maxX, 1, graphColor);
    }
    
    // use Horner's method to compute and return the polynomial evaluated at x
    @Override public Point evaluate(double x) {
        double realx = x;
        x -= maxX/2;
        double y = 0;
        for (double coefficient : coefficients) {
            y = coefficient + x * y;
        }
        return new Point(realx, y);
    }    
}
