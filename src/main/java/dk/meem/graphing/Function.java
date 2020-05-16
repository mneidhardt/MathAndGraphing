package dk.meem.graphing;

/* This is an interface for use with ComplexExponential.
 * The classes implementing this interface are the ones that multiply
 * e^i*pi etc.
 * 
 * I'm wondering whether there's an easier way to do this...
 */
public interface Function {
	
	double evaluate(double x);
	
}
