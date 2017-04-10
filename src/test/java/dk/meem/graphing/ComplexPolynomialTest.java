package dk.meem.graphing;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Arrays;
import org.jscience.mathematics.function.Polynomial;
import org.jscience.mathematics.function.Term;
import org.jscience.mathematics.function.Variable;
import org.jscience.mathematics.number.Complex;
import org.jscience.mathematics.number.Complex;
import org.junit.Test;

public class ComplexPolynomialTest {

	private static final double epsilon = ComplexPolynomial.getEpsilon();
    private static final DecimalFormat form =
        new DecimalFormat(" 0.00000000000000E0;-0.00000000000000E0");

	@Test
	public void test() {
		showResult(1.0, -2.0, 2.0);
	}
	
	   private static void showResult(double... a) {
	        Complex[] ca = ComplexPolynomial.complexArray(a);
	        Polynomial<Complex> px = ComplexPolynomial.create(ca);
	        System.out.println("Poly: " + px);
	        Complex[] r = ComplexPolynomial.roots(ca);
	        validate(ca, r);
	    }
	   
	private static void showResult(Complex... a) {
        Polynomial<Complex> px = ComplexPolynomial.create(a);
        System.out.println("Poly: " + px);
        Complex[] r = ComplexPolynomial.roots(a);
        validate(a, r);
    }
	   // Compare f(r) to zero; print roots; find largest error
    private static void validate(Complex[] ca, Complex... r) {
        double max = 0.0;
        Arrays.sort(r);
        int ix = 0;
        while (ix < r.length) {
            Complex error = ComplexPolynomial.
                eval(ca, r[ix]).minus(Complex.ZERO);
            max = Math.max(max, error.magnitude());
            double re = r[ix].getReal();
            double im = r[ix].getImaginary();
            if (Math.abs(re) < epsilon) re = 0;
            if (Math.abs(im) < epsilon) im = 0;
            if (im == 0) {
                System.out.println("Real: " + form.format(re));
            } else {
                if (ix + 1 < r.length && conjugate(r[ix], r[ix + 1])) {
                    System.out.println("Comp: " + form.format(re)
                        + " +-" + form.format(Math.abs(im)) + "i");
                    ix++;
                } else {
                    System.out.println("Comp: " + form.format(re)
                        + (im < 0 ? " -" : " +")
                        + form.format(Math.abs(im)) + "i");
                }
            }
            ix++;
        }
        System.out.println("Error: "
            + (max < epsilon ? "< " + epsilon : form.format(max))
            + "\n");
    }

    // Return true if a and b are conjugates
    private static boolean conjugate(Complex a, Complex b) {
        double dr = a.getReal() - b.getReal();
        double di = Math.abs(a.getImaginary()) - Math.abs(b.getImaginary());
        return Math.abs(dr) < epsilon && Math.abs(di) < epsilon;
    }
}
