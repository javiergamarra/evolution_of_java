package com.nhpatt.java8.defaultmethods;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultMethodsTests {

	@Test
	public void defaultMethodPreJava8Test() {
		final CalculatorPreJava8 calculator = new CalculatorPreJava8();
		assertThat(5, equalTo(calculator.add(2, 3)));
	}

	@Test
	public void staticMethodPreJava8Test() {
		assertThat(4, equalTo(CalculatorPreJava8.convert(2)));
	}

	@Test
	public void defaultMethodJava8Test() {
		final CalculatorJava8 calculator = new CalculatorJava8();
		assertThat(5, equalTo(calculator.add(2, 3)));
	}

	@Test
	public void staticMethodJava8Test() {
		assertThat(4, equalTo(CalculatorInterfaceJava8.convert(2)));
	}

}
