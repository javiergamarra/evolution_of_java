package com.nhpatt.java8.defaultmethods;

public interface CalculatorInterfaceJava8 {

	Integer MAGIC_CONSTANT = 2;

	default Integer add(final Integer x, final Integer y) {
		return x + y;
	}

	static Integer convert(final Integer x) {
		return x * MAGIC_CONSTANT;
	}

}
