package com.nhpatt.java8.time;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
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
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DateTests {

	private LocalDate date;
	private LocalDateTime dateTime;
	private Calendar oldDate;

	@Before
	public void setUp() {
		date = LocalDate.of(1984, Month.AUGUST, 8);
		dateTime = LocalDateTime.of(1984, Month.AUGUST, 8, 13, 0);

		// Date oldDate = new Date(1984, 7, 8);
		oldDate = GregorianCalendar.getInstance();
		oldDate.set(1984, Calendar.AUGUST, 8);
	}

	@Test
	public void firstMonthStartsAt0Java7Test() {
		oldDate.set(Calendar.MONTH, Calendar.JANUARY);
		assertThat(oldDate.get(Calendar.MONTH), equalTo(0));
	}

	@Test
	public void firstMonthStartsAt1Test() {
		assertThat(DayOfWeek.MONDAY.getValue(), equalTo(1));
	}

	@Test
	public void createLocalDateTest() {

		LocalDate localDate = LocalDate.of(1984, Month.AUGUST, 8);

		assertThat(localDate.getYear(), equalTo(1984));

		Month month = localDate.getMonth();
		String monthName = month.getDisplayName(FULL, new Locale("es"));
		assertThat(monthName, equalTo("agosto"));
		assertThat(month.getValue(), equalTo(8));
		assertThat(localDate.getMonthValue(), equalTo(8));

		assertThat(localDate.getDayOfMonth(), equalTo(8));

		assertTrue(localDate.isLeapYear());
	}

	@Test
	public void createCurrentLocalDateTest() {

		LocalDate now = LocalDate.now();

		Date date = new Date();

		assertThat(now.getYear(), equalTo(date.getYear() + 1900));
		assertThat(now.getMonthValue(), equalTo(date.getMonth() + 1));
		assertThat(now.getDayOfMonth(), equalTo(date.getDate()));
	}

	@Test
	public void retrieveValuesWithTemporalFieldsTest() {

		LocalDate localDate = LocalDate.of(1984, Month.AUGUST, 8);

		assertThat(localDate.get(ChronoField.YEAR), equalTo(1984));
		assertThat(localDate.get(ChronoField.MONTH_OF_YEAR), equalTo(8));
		assertThat(localDate.get(ChronoField.DAY_OF_MONTH), equalTo(8));
	}

	@Test
	public void parseStringDatesTest() {

		LocalDate localDate = LocalDate.parse("1984-08-08");

		assertThat(localDate.get(ChronoField.YEAR), equalTo(1984));
		assertThat(localDate.get(ChronoField.MONTH_OF_YEAR), equalTo(8));
		assertThat(localDate.get(ChronoField.DAY_OF_MONTH), equalTo(8));
	}

	@Test
	public void createLocalTimeTest() {

		LocalTime localTime = LocalTime.of(20, 30, 0);

		assertThat(localTime.getHour(), equalTo(20));
		assertThat(localTime.getMinute(), equalTo(30));
		assertThat(localTime.getSecond(), equalTo(0));
	}

	@Test
	public void createLocalDateTimeTest() {

		LocalDateTime localDateTime =
			LocalDateTime.of(1984, Month.AUGUST, 8, 13, 45, 20);

		assertThat(localDateTime.getYear(), equalTo(1984));
		assertThat(localDateTime.getMonthValue(), equalTo(8));
		assertThat(localDateTime.getDayOfMonth(), equalTo(8));

		assertThat(localDateTime.getHour(), equalTo(13));
		assertThat(localDateTime.getMinute(), equalTo(45));
		assertThat(localDateTime.getSecond(), equalTo(20));
	}


	@Test
	public void createAPeriodTest() throws InterruptedException {

		LocalDate start = LocalDate.now().minusDays(1);

		Thread.sleep(1000);

		LocalDate end = LocalDate.now();

		Period between = Period.between(start, end);

		assertThat(between.getDays(), equalTo(1));
	}

	@Test
	public void javierIsOldTest() {

		final int age = Period.between(date, LocalDate.now()).getYears();

		assertThat(age, greaterThan(21));
	}

	@Test
	public void addTwoDaysToLocalDateTest() {

		LocalDate now = LocalDate.of(2010, 1, 3);

		LocalDate localDate = now.plusDays(2);

		assertThat(localDate.getDayOfMonth(), equalTo(now.getDayOfMonth() + 2));

		LocalDate plus = now.plus(Period.ofDays(2));

		assertThat(plus.getDayOfMonth(), equalTo(now.getDayOfMonth() + 2));

		LocalDate afterTwoDays = now.withDayOfMonth(now.getDayOfMonth() + 2);

		assertThat(
			afterTwoDays.getDayOfMonth(), equalTo(now.getDayOfMonth() + 2));
	}

	@Test
	public void plusDaysTest() {
		LocalDateTime plusHours5 = dateTime.plusHours(5);
		LocalDateTime plus5Hours = dateTime.plus(5, HOURS);
		LocalDateTime plusDuration = dateTime.plus(Duration.of(5, HOURS));

		assertThat(plusHours5.getHour(), equalTo(18));
		assertThat(plus5Hours.getHour(), equalTo(18));
		assertThat(plusDuration.getHour(), equalTo(18));
	}

	@Test
	public void minusDaysJava7Test() {
		oldDate.set(
			Calendar.DAY_OF_YEAR,
			oldDate.get(Calendar.DAY_OF_YEAR) - 2);

		assertThat(oldDate.get(Calendar.DAY_OF_MONTH), equalTo(6));
		assertThat(oldDate.get(Calendar.MONTH), equalTo(7));
		assertThat(oldDate.get(Calendar.YEAR), equalTo(1984));
	}

	@Test
	public void minusDaysTest() {
		final LocalDate sixOfAugust = date.minusDays(2);

		assertThat(sixOfAugust.getDayOfMonth(), equalTo(6));
		assertThat(sixOfAugust.getMonth(), equalTo(Month.AUGUST));
		assertThat(sixOfAugust.getYear(), equalTo(1984));
	}

	@Test
	public void nextWednesdayTest() {
		final LocalDate date = LocalDate.of(2013, Month.DECEMBER, 7);

		final LocalDate nextWednesday = date.with(TemporalAdjusters
			.next(DayOfWeek.WEDNESDAY));

		assertThat(nextWednesday.getDayOfMonth(), equalTo(11));
	}

	@Test
	public void atStartOfDayJava7Test() {
		oldDate.set(Calendar.HOUR_OF_DAY, 0);
		oldDate.set(Calendar.MINUTE, 0);

		assertThat(oldDate.get(Calendar.HOUR_OF_DAY), equalTo(0));
		assertThat(oldDate.get(Calendar.MINUTE), equalTo(0));
	}

	@Test
	public void atStartOfDayTest() {
		final LocalDateTime newDay = date.atStartOfDay();

		assertThat(newDay.getHour(), equalTo(0));
		assertThat(newDay.getMinute(), equalTo(0));
	}

	@Test
	public void instantTimeTest() {
		Long millis = Clock.systemUTC().millis();

		assertThat(System.currentTimeMillis(),
				allOf(greaterThan(millis - 1000), lessThan(millis + 1000)));
	}

	@Test
	public void youCanFormatInStandardISOJava7Test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		assertThat(sdf.format(oldDate.getTime()), equalTo("1984-08-08"));
	}

	@Test
	public void youCanFormatInStandardISOTest() {
		String dateText = DateTimeFormatter.ISO_DATE.format(date);

		assertThat(dateText, equalTo("1984-08-08"));
	}

	@Test
	public void youCanFormatEasilyAsStringJava7Test() {
		String dateText = String.format("%s-%s-%s", oldDate.get(Calendar.YEAR),
				oldDate.get(Calendar.MONTH) + 1,
				oldDate.get(Calendar.DAY_OF_MONTH));

		assertThat(dateText, equalTo("1984-8-8"));
	}

	@Test
	public void youCanFormatEasilyAsStringTest() {
		String dateText = String.format("%s-%s-%s", date.getYear(),
				date.getMonthValue(), date.getDayOfMonth());

		assertThat(dateText, equalTo("1984-8-8"));
	}

	@Test
	public void firstMonthStartsAt0Java7Test() {
		oldDate.set(Calendar.MONTH, Calendar.JANUARY);
		assertThat(oldDate.get(Calendar.MONTH), equalTo(0));
	}

	@Test
	public void firstMonthStartsAt1Test() {
		assertThat(DayOfWeek.MONDAY.getValue(), equalTo(1));
	}

	@Test
	public void javierIsOldTest() {

		final int age = Period.between(date, LocalDate.now()).getYears();

		assertThat(age, greaterThan(21));
	}

	@Test
	public void nextWednesdayTest() {
		final LocalDate date = LocalDate.of(2013, Month.DECEMBER, 7);

		final LocalDate nextWednesday = date.with(TemporalAdjusters
				.next(DayOfWeek.WEDNESDAY));

		assertThat(nextWednesday.getDayOfMonth(), equalTo(11));
	}

	@Test
	public void flightTimeTest() {
		final LocalTime dateSpain = LocalTime.of(12, 00);
		final LocalTime dateNY = LocalTime.of(15, 00);
		Duration duration = Duration.between(
				dateSpain.atOffset(ZoneOffset.of("+0")),
				dateNY.atOffset(ZoneOffset.of("-5")));

		assertThat(duration.toHours(), equalTo(8L));
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

		assertThat(duration.toHours(), equalTo(8L));
	}
}
