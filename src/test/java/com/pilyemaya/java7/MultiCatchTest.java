package com.pilyemaya.java7;

import org.junit.Test;

public class MultiCatchTest {

	@Test
	public void singleCatchTest() {
		divideWithSingleCatch("1", "0");
	}

	@Test
	public void multiCatchTest() {
		divideWithMultipleCatch("1", "0");
	}

	public double divideWithSingleCatch(final String number1,
			final String number2) {
		double result = 0d;
		try {
			int a = Integer.parseInt(number1);
			int b = Integer.parseInt(number2);
			result = a / b;
		} catch (NumberFormatException ex1) {
			System.out.println("Not a valid number");
		} catch (ArithmeticException ex2) {
			System.out.println("Division by zero");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double divideWithMultipleCatch(final String number1,
			final String number2) {
		double result = 0d;
		try {
			int a = Integer.parseInt(number1);
			int b = Integer.parseInt(number2);
			result = a / b;
		} catch (NumberFormatException | ArithmeticException ex) {
			System.out.println("Not a valid number or division zero");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
