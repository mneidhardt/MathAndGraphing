package dk.meem.graphing;

import java.security.SecureRandom;

public class RandomFunc implements Function {
	SecureRandom random;
	
	public RandomFunc() {
		random = new SecureRandom();
	}
	
	public double evaluate(double x) {
		
		return random.nextInt(3);
	}
}
