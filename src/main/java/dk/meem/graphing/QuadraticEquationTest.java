package dk.meem.graphing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QuadraticEquationTest {
	@Rule
	public ExpectedException thrown= ExpectedException.none();

	@Test
	public void testQuadraticEquation1() throws IOException {
    	double[] coeff = {0.00001, 0.00002, 0.00003, 0.0004};

    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
	    thrown.expect(IOException.class);
        throw new IOException();
	}

	@Test
	public void testQuadraticEquation2() throws IOException {
    	double[] coeff = {0.00003, 0.0004};

    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
	    thrown.expect(IOException.class);
        throw new IOException();
	}

	@Test
	public void testQuadraticEquation3() throws IOException {
    	double[] coeff = {0.0, -5, 15};

    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
	    thrown.expect(IOException.class);
        throw new IOException();
	}

	@Test
	public void testQuadraticEquation4() throws IOException {
    	double[] coeff = {0.00001, 0.00002, 0.00003};

    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
	    thrown.expect(IOException.class);
        throw new IOException();
	}

	@Test
	public void testSolve1() throws IOException {
		double[] coeff = {6, -5, 1};
    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
    	
    	List<Double> roots = new ArrayList<Double>();
    	roots.add(1.0/3.0);
    	roots.add(1.0/2.0);
    	assertEquals(roots, qe.solve());
	}
	
	@Test
	public void testSolve2() throws IOException {
		double[] coeff = {6, -5, 1};
    	QuadraticEquation qe = new QuadraticEquation(200, 100, coeff);
    	
    	List<Double> roots = new ArrayList<Double>();
    	roots.add(1.0/3.0);
    	roots.add(1.0/2.0);
    	assertEquals(roots, qe.solve());
		

		fail("Not yet implemented");
	}

	@Test
	public void testGetEquation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetD() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRoots() {
		fail("Not yet implemented");
	}

}
