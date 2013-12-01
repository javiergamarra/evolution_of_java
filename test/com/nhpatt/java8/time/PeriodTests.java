package com.nhpatt.java8.time;

import static org.hamcrest.number.OrderingComparison.lessThan;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import org.junit.Assert;
import org.junit.Test;

public class PeriodTests {

	@Test
	public void javierIsOldTest() {

		final LocalDate birthdate = LocalDate.of(1984, Month.AUGUST, 8);

		final int age = Period.between(birthdate, LocalDate.now()).getYears();

		Assert.assertThat(21, lessThan(age));
	}
}
