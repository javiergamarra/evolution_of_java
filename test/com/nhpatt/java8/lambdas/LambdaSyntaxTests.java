package com.nhpatt.java8.lambdas;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.function.Supplier;

import org.junit.Test;

public class LambdaSyntaxTests {

	@Test
	public void implementingInterfaceJava7WayTest() {
		final CalculatorInterface calculator = new CalculatorInterface() {

			@Override
			public Integer add(final Integer x, final Integer y) {
				return x + y;
			}
		};

		assertThat(5, equalTo(calculator.add(2, 3)));
	}

	@Test
	public void implementingInterfaceJava8WayTest() {
		final CalculatorInterface calculator = (final Integer x, final Integer y) -> {
			return x + y;
		};

		assertThat(5, equalTo(calculator.add(2, 3)));
		// (int x, int y) -> { return x + y; }
	}

	@Test
	public void implementingInterfaceJava8WayImprovedTest() {
		final CalculatorInterface calculator = (x, y) -> {
			return x + y;
		};
		assertThat(5, equalTo(calculator.add(2, 3)));
		// (x, y) -> x + y
	}

	@Test
	public void implementingInterfaceJava8WayReallyImprovedTest() {
		final CalculatorInterface calculator = (x, y) -> x + y;

		assertThat(5, equalTo(calculator.add(2, 3)));
	}

	@Test
	public void implementingOnClickJava7WayTest() {
		final ActionListener listener = new ActionListener() {

			@Override
			public void onClick(final String message) {
				System.out.println(message);
			}
		};
		listener.onClick("Ack!");
	}

	@Test
	public void implementingOnClickJava8WayTest() {
		final ActionListener listener = (final String message) -> {
			System.out.println(message);
		};
		listener.onClick("Ack!");
	}

	@Test
	public void implementingOnClickJava8WayImprovedTest() {
		final ActionListener listener = (message) -> System.out
				.println(message);
		listener.onClick("Ack!");
	}

	@Test
	public void implementingOnClickJava8WayWayImprovedTest() {
		final ActionListener listener = System.out::println;
		listener.onClick("Ack!");
	}

	@Test
	public void functionalInterfaceTest() {
		final Supplier<String> supplier = "hi"::toUpperCase;

		assertThat("HI", equalTo(supplier.get()));
	}

}
