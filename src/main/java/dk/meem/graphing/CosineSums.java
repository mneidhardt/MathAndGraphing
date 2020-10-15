package dk.meem.graphing;

public class CosineSums implements Function {
	
	public double evaluate(double x) {
		//return 0.3*Math.cos(x)+Math.cos(5*x)+Math.cos(120*x);
		//return 0.3*Math.cos(0.01*x)+Math.cos(0.005*x)+Math.cos(5.7*x);
		return 0.3*Math.cos(0.01*x)+Math.cos(0.005*x)+Math.cos(5.7*x)+0.001*Math.cos(x);
		//return Math.cos(x)+Math.sin(x);
	}
}
