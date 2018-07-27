package com.nhpatt.java8.defaultmethods;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DefaultMethodsTests {

	@Test
	public void defaultMethodPreJava8Test() {
		final CalculatorPreJava8 calculator = new CalculatorPreJava8();
		assertThat(calculator.add(2, 3), equalTo(5));
	}

	@Test
	public void staticMethodPreJava8Test() {
		assertThat(CalculatorPreJava8.convert(2), equalTo(4));
	}

	@Test
	public void defaultMethodJava8Test() {
		final CalculatorJava8 calculator = new CalculatorJava8();
		assertThat(calculator.add(2, 3), equalTo(5));
	}

	@Test
	public void staticMethodJava8Test() {
		assertThat(CalculatorInterfaceJava8.convert(2), equalTo(4));
	}

}
