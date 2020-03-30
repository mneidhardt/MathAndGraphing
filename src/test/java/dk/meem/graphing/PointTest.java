package dk.meem.graphing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PointTest {

	@Test
	public void testPoint1() {
		double xval = 100d;
		double yval = 300.05;
		Point p = new Point(xval, yval);
		assertEquals(p.getX(), xval, 0.0);
		assertEquals(p.getY(), yval, 0.0);
		assertEquals(p.getXInt(), (int)Math.round(xval));
		assertEquals(p.getYInt(), (int)Math.round(yval));
		
		double factor = 2.5;
		Point p2 = p.multBy(factor);
		assertEquals(p2.getX(), factor*xval, 0.0);
		assertEquals(p2.getY(), factor*yval, 0.0);

		
	}
/*
	@Test
	public void testQuadraticEquation3() throws IOException {
    	double[] coeff = {0, -5, 5*3};
    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
    	
    	Set<Double> roots = new HashSet<Double>();
    	roots.add(3d);
    	assertEquals(roots, qe.solve());
	}

	@Test
	public void testQuadraticEquation3b() throws IOException {
    	double[] coeff = {0, 5, 5*3};
    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
    	
    	Set<Double> roots = new HashSet<Double>();
    	roots.add(-3d);
    	assertEquals(roots, qe.solve());
	}

	@Test
	public void testSolve1() throws IOException {
		double[] coeff = {1, -2, 2};
    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
    	
    	Set<Double> roots = new HashSet<Double>();
    	assertEquals(roots, qe.solve());
	}
	
	@Test
	public void testSolve2() throws IOException {
		double[] coeff = {6, -5, 1};
    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
    	
    	Set<Double> roots = new HashSet<Double>();
    	roots.add(1.0/3.0);
    	roots.add(1.0/2.0);
    	assertEquals(roots, qe.solve());
	}
*/
}
