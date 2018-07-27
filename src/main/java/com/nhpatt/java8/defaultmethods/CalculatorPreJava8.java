package com.nhpatt.java8.defaultmethods;

public class CalculatorPreJava8 implements CalculatorInterfacePreJava8 {

	@Override
	public Integer add(final Integer x, final Integer y) {
		return x + y;
	}

	public static Integer convert(final Integer x) {
		return x * MAGIC_CONSTANT;
	}

}
