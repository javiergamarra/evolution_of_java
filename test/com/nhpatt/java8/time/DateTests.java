package com.nhpatt.java8.time;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Before;
import org.junit.Test;

public class DateTests {

	private LocalDate date;
	private LocalDateTime dateTime;

	@Before
	public void setUp() {
		date = LocalDate.of(1984, Month.AUGUST, 8);
		dateTime = LocalDateTime.of(1984, Month.AUGUST, 8, 13, 0);
	}

	@Test
	public void minusDaysTest() {
		final LocalDate sixOfAugust = date.minusDays(2);

		assertThat(6, equalTo(sixOfAugust.getDayOfMonth()));
		assertThat(Month.AUGUST, equalTo(sixOfAugust.getMonth()));
		assertThat(1984, equalTo(sixOfAugust.getYear()));
	}

	@Test
	public void plusDaysTest() {
		LocalDateTime plusHours5 = dateTime.plusHours(5);
		LocalDateTime plus5Hours = dateTime.plus(5, HOURS);
		LocalDateTime plusDuration = dateTime.plus(Duration.of(5, HOURS));

		assertThat(18, equalTo(plusHours5.getHour()));
		assertThat(18, equalTo(plus5Hours.getHour()));
		assertThat(18, equalTo(plusDuration.getHour()));
	}

	@Test
	public void atStartOfDayTest() {
		final LocalDate date = LocalDate.of(1984, Month.AUGUST, 8);
		final LocalDateTime newDay = date.atStartOfDay();

		assertThat(0, equalTo(newDay.getHour()));
		assertThat(0, equalTo(newDay.getMinute()));
	}

	@Test
	public void instantTimeTest() {
		Long millis = Clock.systemUTC().millis();

		assertThat(System.currentTimeMillis(),
				allOf(greaterThan(millis - 1000), lessThan(millis + 1000)));
	}

	@Test
	public void youCanFormatInStandardISOTest() {
		String dateText = DateTimeFormatter.ISO_DATE.format(date);

		assertThat("1984-08-08", equalTo(dateText));
	}

	@Test
	public void youCanFormatEasilyAsStringTest() {
		String dateText = String.format("%s-%s-%s", date.getYear(),
				date.getMonthValue(), date.getDayOfMonth());

		assertThat("1984-8-8", equalTo(dateText));
	}

	@Test
	public void firstMonthStartsAt1Test() {
		assertThat(1, equalTo(Month.JANUARY.getValue()));
	}

	@Test
	public void javierIsOldTest() {
		final LocalDate birthdate = LocalDate.of(1984, Month.AUGUST, 8);
		final int age = Period.between(birthdate, LocalDate.now()).getYears();

		assertThat(21, lessThan(age));
	}

	@Test
	public void nextWednesdayTest() {
		final LocalDate date = LocalDate.of(2013, Month.DECEMBER, 7);
		final LocalDate nextWednesday = date.with(TemporalAdjusters
				.next(DayOfWeek.WEDNESDAY));

		assertThat(11, equalTo(nextWednesday.getDayOfMonth()));
	}

	@Test
	public void flightTimeTest() {
		final LocalTime dateSpain = LocalTime.of(12, 00);
		final LocalTime dateNY = LocalTime.of(15, 00);
		Duration duration = Duration.between(
				dateSpain.atOffset(ZoneOffset.of("+0")),
				dateNY.atOffset(ZoneOffset.of("-5")));

		assertThat(8L, equalTo(duration.toHours()));
	}

	@Test
	public void flightTimeWithZonedDatesTest() {
		final LocalTime dateSpain = LocalTime.of(12, 00);
		final LocalTime dateNY = LocalTime.of(15, 00);
		
		
		ZonedDateTime london = ZonedDateTime.of(LocalDate.now(), dateSpain,
				ZoneId.of("Europe/London"));
		ZonedDateTime newYork = ZonedDateTime.of(LocalDate.now(), dateNY,
				ZoneId.of("America/New_York"));
		Duration duration = Duration.between(london, newYork);

		assertThat(8L, equalTo(duration.toHours()));
	}
}
