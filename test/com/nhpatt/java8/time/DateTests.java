package com.nhpatt.java8.time;

import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import org.junit.Test;

public class DateTests {

	@Test
	public void javierIsOldTest() {

		final LocalDate birthdate = LocalDate.of(1984, Month.AUGUST, 8);

		final int age = Period.between(birthdate, LocalDate.now()).getYears();

		assertThat(21, lessThan(age));
	}
}
