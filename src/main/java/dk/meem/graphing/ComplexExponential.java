package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

/* This started as an attempt to emulate the display of the Fourier Transform by 3Blue1Brown,
 * i.e. displaying a function wrapped around a circle.
 * 
 */
public class ComplexExponential {
	Color graphColor = new Color(50,75,200);
	
	public void draw(Graphics2D g) {
		g.setColor(this.graphColor);
		Point p = new Point(150d, 150d);
		Point ptranslate = new Point(300d, 300d);

		for (double t = 0; t <= 2 * Math.PI; t += (Math.PI / 512)) {
			Point p2 = p.add(ptranslate);

			double sinval = 1 + 0.5 * Math.sin(32*t);

			Point p3 = p.multBy(sinval).add(ptranslate);

			g.drawLine(p2.getXInt(), p2.getYInt(), p3.getXInt(), p3.getYInt());
			p = p.rotate(t);
		}
	}
}
