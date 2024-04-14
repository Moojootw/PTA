package com.promineotech;

import java.util.Random;

public class TestDemo {
	
	public int addPositive(int a, int b) throws Exception {
		//takes two inputs of positive integers (zero inclusive) and adds them together, or else throw exception
		if (a > 0 && b > 0) {
			return a + b;}
		else {
			throw new Exception("Both parameters must be positive!");
			}
		}
	
	public int truncateDouble(double value) {
		//a pretty benign function that converts a double to an integer, this is just for demonstration.
		return (int) value;
	}
	
	public int getRandomInt() {
		//generates an integer 0-9 then adds one to the result (1 to 10)
		Random random = new Random();
		return random.nextInt(10) + 1;
	}
	
	public int randomNumberSquared() {
		//calls the getRandomInt method and squares it for the return
		int intToSquare = getRandomInt();
		return intToSquare * intToSquare;
	}

	
}
