package com.nhpatt.java8.lambdas;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class LambdaStreamsTests {

	private List<String> strings;
	private List<User> users;
	private List<User> coolUsers;
	private List<Integer> numbers;
	private Integer numberOperations;

	@Before
	public void setUp() {
		strings = Arrays.asList("Lambdas", "are", "cool");
		users = Arrays.asList(new User("javier", "gamarra"), new User("pilar",
				"fraile"));
		coolUsers = Arrays.asList(new User("javier", "gamarra", 10), new User(
				"pilar", "fraile", 20), new User("david", "hasselhoff", 100));

		numbers = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6 });
		numberOperations = 0;
	}

	private void doSomething(final String text) {
		System.out.println(text.toUpperCase());
	}

	@Test
	public void classicForEachTest() {
		for (final String text : strings) {
			doSomething(text);
		}
	}

	@Test
	public void newForEachTest() {
		strings.forEach((x) -> {
			doSomething(x);
		});
	}

	@Test
	public void reallyNewForEachTest() {
		strings.forEach(this::doSomething);
	}

	@Test
	public void classicFilteredForEachTest() {
		List<String> longWords = new ArrayList<String>();
		for (final String text : strings) {
			if (text.length() > 3) {
				longWords.add(text);
			}
		}

		assertThat(longWords.size(), equalTo(2));
		assertThat(longWords.get(0), equalTo("Lambdas"));
		assertThat(longWords.get(1), equalTo("cool"));
	}

	@Test
	public void newFilteredForEachTest() {
		List<String> longWords = strings.stream().filter(e -> e.length() > 3)
				.collect(Collectors.<String> toList());

		assertThat(longWords.size(), equalTo(2));
		assertThat(longWords.get(0), equalTo("Lambdas"));
		assertThat(longWords.get(1), equalTo("cool"));
	}

	@Test
	public void classicFilteredAgainForEachTest() {
		List<String> longWords = new ArrayList<String>();
		for (final String text : strings) {
			if (text.contains("cool")) {
				longWords.add(text);
			}
		}

		assertThat(longWords.size(), equalTo(1));
		assertThat(longWords.get(0), equalTo("cool"));
	}

	private List<String> getFilteredWords(Predicate<String> predicate) {
		return strings.stream().filter(predicate)
				.collect(Collectors.<String> toList());
	}

	public boolean hola(String text) {
		return text.contains("cool");
	}

	@Test
	public void newFilteredAgainForEachTest() {
		List<String> longWords = getFilteredWords(this::hola);

		assertThat(longWords.size(), equalTo(1));
		assertThat(longWords.get(0), equalTo("cool"));
	}

	@Test
	public void sortingUsersTest() {
		Collections.sort(users,
				(x, y) -> x.getSurname().compareTo(y.getSurname()));

		assertThat(users.get(0).getSurname(), equalTo("fraile"));
	}

	@Test
	public void gettingCoolestUserTest() {
		Integer maxCool = 0;
		User coolestUser = null;
		for (User user : coolUsers) {
			if (maxCool <= user.getCoolnessFactor()) {
				coolestUser = user;
			}
		}

		assertThat(coolestUser.getName(), equalTo("david"));
	}

	public User coolest(User user1, User user2) {
		return user1.getCoolnessFactor() > user2.getCoolnessFactor() ? user1
				: user2;
	}

	@Test
	public void gettingCoolestUserWithJava8Test() {
		User coolestUser = coolUsers.stream().reduce(this::coolest).get();

		assertThat(coolestUser.getName(), equalTo("david"));
	}

	@Test
	public void gettingNullUserTest() {
		User coolestUser = (User) coolUsers.stream()
				.filter(x -> "juan".equals(x.getName())).map(x -> {
					x.setCoolnessFactor(100);
					return x;
				}).findFirst().orElse(new User("-", "-"));
		assertThat(coolestUser.getName(), equalTo("-"));
	}

	@Test
	public void gettingPilarUserTest() {
		User coolestUser = (User) coolUsers.stream()
				.filter(x -> "pilar".equals(x.getName())).map(x -> {
					x.setCoolnessFactor(100);
					return x;
				}).findFirst().orElse(new User("-", "-"));

		assertThat(coolestUser.getName(), equalTo("pilar"));
		assertThat(coolestUser.getCoolnessFactor(), equalTo(100));
	}

	@Test
	public void infiniteLazyStreamTest() {
		Stream<Integer> integers = Stream.iterate(1, i -> i + 1);
		integers.mapToInt(i -> i * 2);
	}

	@Test
	public void infiniteStreamTest() {
		Stream<Integer> integers = Stream.iterate(1, i -> i + 1);
		int[] result = integers.mapToInt(i -> i * 2).limit(5).toArray();

		assertThat(result, equalTo(new int[] { 2, 4, 6, 8, 10 }));
	}

	@Test
	public void parallelLazyStreamTest() {
		List<Integer> integer = Arrays.asList(new Integer[] { 2, 4, 6, 8, 10 });
		integer.parallelStream().map((Integer x) -> {
			x = x * 2;
			System.out.println(x);
			return x;
		});
	}

	@Test
	public void parallelStreamTest() {
		List<Integer> integer = Arrays.asList(new Integer[] { 2, 4, 6, 8, 10 });
		int count = integer.parallelStream().mapToInt(i -> i * 2)
				.reduce((x, y) -> x + y).getAsInt();

		assertThat(count, equalTo(60));
	}

	@Test
	public void youAreSoEagerTest() {
		List<Integer> numbers = Arrays
				.asList(new Integer[] { 1, 2, 3, 4, 5, 6 });

		List<Integer> evenNumbers = new ArrayList<>();
		for (int n : numbers) {
			if (isEven(n)) {
				evenNumbers.add(n);
			}
		}

		List<Integer> multipliedNumbers = new ArrayList<>();
		for (int n : evenNumbers) {
			multipliedNumbers.add(doubleIt(n));
		}

		List<Integer> greaterThan5Numbers = new ArrayList<>();
		for (int n : multipliedNumbers) {
			if (isGreaterThan5(n)) {
				greaterThan5Numbers.add(n);
			}
		}

		System.out.println("first number is " + greaterThan5Numbers.get(0));
		assertThat(greaterThan5Numbers.get(0), equalTo(8));
	}

	private boolean isEven(int n) {
		System.out.println(numberOperations + " - Is " + n + " even?");
		numberOperations++;
		return n % 2 == 0;
	}

	private int doubleIt(int n) {
		System.out.println(numberOperations + " - we double " + n);
		numberOperations++;
		return n * 2;
	}

	private boolean isGreaterThan5(int n) {
		System.out.println(numberOperations + " - Is " + n + " greater than 5?");
		numberOperations++;
		return n > 5;
	}

	@Test
	public void youAreSoLazyLazyTest() {

		OptionalInt result = numbers.stream().filter(this::isEven)
				.mapToInt(this::doubleIt).filter(this::isGreaterThan5)
				.findFirst();

		System.out.println("first number is " + result.getAsInt());
	}
}
