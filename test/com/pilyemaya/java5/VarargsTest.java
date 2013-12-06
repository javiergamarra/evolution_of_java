package com.pilyemaya.java5;

import org.junit.Assert;
import org.junit.Test;

public class VarargsTest {

	@Test
	public void sumTwoNumbersTest() {
		Assert.assertEquals(4, sumTwoNumbers(2, 2));
		final int[] numbers = new int[] { 2, 2 };
		Assert.assertEquals(4, sumNumbersUsingAnArray(numbers));
		Assert.assertEquals(4, sumNumbersUsingVarargs(2, 2));
	}

	@Test
	public void sumThreeNumbersTest() {
		Assert.assertEquals(6, sumThreeNumbers(2, 2, 2));
		final int[] numbers = new int[] { 2, 2, 2 };
		Assert.assertEquals(6, sumNumbersUsingAnArray(numbers));
		Assert.assertEquals(6, sumNumbersUsingVarargs(2, 2, 2));
	}

	@Test
	public void sumSixNumbersTest() {
		Assert.assertEquals(12, sumSixNumbers(2, 2, 2, 2, 2, 2));
		final int[] numbers = new int[] { 2, 2, 2, 2, 2, 2 };
		Assert.assertEquals(12, sumNumbersUsingAnArray(numbers));
		Assert.assertEquals(12, sumNumbersUsingVarargs(2, 2, 2, 2, 2, 2));
	}

	public int sumTwoNumbers(final int a, final int b) {
		return a + b;
	}

	public int sumThreeNumbers(final int a, final int b, final int c) {
		return a + b + c;
	}

	public int sumSixNumbers(final int a, final int b, final int c,
			final int d, final int e, final int f) {
		return a + b + c + d + e + f;
	}

	public int sumNumbersUsingAnArray(final int[] numbers) {
		int total = 0;
		for (final int number : numbers) {
			total += number;
		}
		return total;
	}

	public int sumNumbersUsingVarargs(final int... numbers) {
		int total = 0;
		for (final int number : numbers) {
			total += number;
		}
		return total;
	}

}
