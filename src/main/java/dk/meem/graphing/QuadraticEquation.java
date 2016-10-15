package dk.meem.graphing;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuadraticEquation extends Polynomial {
	private double d;
	private List<Double> roots;
	
	public QuadraticEquation(int maxX, int maxY, double[] coefficients) throws IOException {
		super(maxX, maxY, coefficients);
		
		if (coefficients.length != 3) {
			throw new IOException("Must have exactly 3 coefficients.");
		} else if (coefficients[0] == 0) {
			throw new IOException("'a' cannot be zero.");
		}
	}
	
	public void solve() {
		roots = new ArrayList<Double>();
		this.d = Math.pow(this.coefficients[1], 2) - 4*this.coefficients[0]*this.coefficients[2];
		
		if (d > 0.0) {
			roots.add((-this.coefficients[1]-Math.sqrt(d))/(2*this.coefficients[0]));			
			roots.add((-this.coefficients[1]+Math.sqrt(d))/(2*this.coefficients[0]));
		} else if (d == 0) {
			roots.add((-this.coefficients[1])/2*this.coefficients[0]);
		} else {
			// System.out.println("d < 0, kun komplekse rødder.");
		}
	}
	
	public String getEquation() {
		return this.coefficients[0] + "x^2 + " +
	           this.coefficients[1] + "x + " +
			   this.coefficients[2] + " = 0.";
	}

	public double getD() {
		return d;
	}

	public List<Double> getRoots() {
		return roots;
	}
}
