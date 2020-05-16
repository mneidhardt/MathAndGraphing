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
	double frequency = 1.0;
	final Function f;
	final double increment = Math.PI/1024;
	final double factor = 180.0;
	double exp;
	double t;
	
	public ComplexExponential(Function f) {
		this.f = f;
	}
	
	// Draw using line between one point and the next.
	public void drawEIP(Graphics2D g) {
		g.setColor(this.graphColor);
		Point ptranslate = new Point(380d, 380d);

		g.drawString("Frequency    = " + this.frequency, 20, 20);
		g.drawArc(ptranslate.getXInt()/2, ptranslate.getYInt()/2, 380, 380, 0, 360);
		//straightGraph(g);
		
		t = 0.0;
		exp = 2*Math.PI*frequency;
		
		Point p = new Point(Math.cos(exp*t), Math.sin(exp*t));
		Point prev = p.multBy(f.evaluate(t)).multBy(factor).add(ptranslate);
		
		for (t = increment; t <= 2*Math.PI; t += increment) {
			p = new Point(Math.cos(exp*t), Math.sin(exp*t));
			Point pnext = p.multBy(f.evaluate(t)).multBy(factor).add(ptranslate);

			g.drawLine(prev.getXInt(), prev.getYInt(), pnext.getXInt(), pnext.getYInt());
			
			prev = pnext;
		}
	}
	
	private void straightGraph(Graphics2D g) {
		// Draw ordinary curve along x-axis:
		//for (double t=0.0; t<2*Math.PI; t += Math.PI/256) {
		System.out.println("Function= " + f.getClass().getName());
		for (double t=0.0; t<200; t += 1) {
			//double y = Math.cos(t);
			double y = f.evaluate(t);
			g.drawArc((int)t*2, (int)(200+y*40), 2, 2, 0, 360);
		}
	}
	
	// Draw using dots.
	public void drawEIP1(Graphics2D g) {
		g.setColor(this.graphColor);
		Point ptranslate = new Point(380d, 380d);

		g.drawString("Frequency    = " + this.frequency, 20, 20);
		g.drawArc(ptranslate.getXInt()/2, ptranslate.getYInt()/2, 380, 380, 0, 360);

		for (double t = 0.0; t <= 2*Math.PI; t += Math.PI/1024) {
			Point p = new Point(Math.cos(2*Math.PI*t*frequency), Math.sin(2*Math.PI*t*this.frequency));
			Point p2 = p.multBy(Math.cos(1/t)).multBy(300).add(ptranslate);
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
