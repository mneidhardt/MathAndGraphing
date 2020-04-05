package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.List;

/* This started as an attempt to emulate the display of the Fourier Transform by 3Blue1Brown,
 * i.e. displaying a function wrapped around a circle.
 * 
 */
public class ComplexExponential {
	Color graphColor = new Color(50,75,200);
	double frequency = 0.01;
	
	public void drawEIP(Graphics2D g) {
		g.setColor(this.graphColor);
		Point ptranslate = new Point(380d, 380d);

		g.drawString("Frequency    = " + this.frequency, 20, 20);
		g.drawArc(ptranslate.getXInt()/2, ptranslate.getYInt()/2, 380, 380, 0, 360);

		for (double t = 0.0; t <= 2*Math.PI; t += Math.PI/32) {
			Point p = new Point(Math.cos(2*Math.PI*t*frequency), Math.sin(2*Math.PI*t*this.frequency));
			Point p2 = p.multBy(Math.sin(t)).multBy(250).add(ptranslate);
			g.drawArc(p2.getXInt(), p2.getYInt(), 2, 2, 0, 360);
		}
		
	}
	
	private String roundNumber(double d) {
    	DecimalFormat df = new DecimalFormat("#############.######");
    	return df.format(d);
    }
    	
	public void incFrequency() {
		this.frequency += 0.01;
	}
	public void decFrequency() {
		this.frequency -= 0.01;
	}
}
