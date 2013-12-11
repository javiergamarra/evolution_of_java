package com.nhpatt.java8.lambdas;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class LambdaStreamsTests {

	private List<String> strings;
	private List<User> users;
	private List<User> coolUsers;

	@Before
	public void setUp() {
		strings = Arrays.asList("Lambdas", "are", "cool");
		users = Arrays.asList(new User("javier", "gamarra"), new User("pilar",
				"fraile"));
		coolUsers = Arrays.asList(new User("javier", "gamarra", 10), new User(
				"pilar", "fraile", 20), new User("david", "hasselhoff", 100));
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
		assertThat(2, equalTo(longWords.size()));
		assertThat("Lambdas", equalTo(longWords.get(0)));
		assertThat("cool", equalTo(longWords.get(1)));
	}

	@Test
	public void newFilteredForEachTest() {
		List<String> longWords = strings.stream().filter(e -> e.length() > 3)
				.collect(Collectors.<String> toList());

		assertThat(2, equalTo(longWords.size()));
		assertThat("Lambdas", equalTo(longWords.get(0)));
		assertThat("cool", equalTo(longWords.get(1)));
	}

	@Test
	public void classicFilteredAgainForEachTest() {
		List<String> longWords = new ArrayList<String>();
		for (final String text : strings) {
			if (text.contains("cool")) {
				longWords.add(text);
			}
		}
		assertThat(1, equalTo(longWords.size()));
		assertThat("cool", equalTo(longWords.get(0)));
	}

	private List<String> getFilteredWords(Predicate<String> predicate) {
		return strings.stream().filter(predicate)
				.collect(Collectors.<String> toList());
	}

	@Test
	public void newFilteredAgainForEachTest() {
		List<String> longWords = getFilteredWords(e -> e.contains("cool"));

		assertThat(1, equalTo(longWords.size()));
		assertThat("cool", equalTo(longWords.get(0)));
	}

	@Test
	public void sortingUsersTest() {
		Collections.sort(users,
				(x, y) -> x.getSurname().compareTo(y.getSurname()));

		assertThat("fraile", equalTo(users.get(0).getSurname()));
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

		assertThat("david", equalTo(coolestUser.getName()));
	}

	public User coolest(User user1, User user2) {
		return user1.getCoolnessFactor() > user2.getCoolnessFactor() ? user1
				: user2;
	}

	@Test
	public void gettingCoolestUserWithJava8Test() {
		User coolestUser = coolUsers.stream().reduce(this::coolest).get();

		assertThat("david", equalTo(coolestUser.getName()));
	}

	@Test
	public void gettingNullUserTest() {
		User coolestUser = (User) coolUsers.stream()
				.filter(x -> "juan".equals(x.getName())).map(x -> {
					x.setCoolnessFactor(100);
					return x;
				}).findFirst().orElse(new User("-", "-"));

		assertThat("-", equalTo(coolestUser.getName()));
	}

	@Test
	public void gettingPilarUserTest() {
		User coolestUser = (User) coolUsers.stream()
				.filter(x -> "pilar".equals(x.getName())).map(x -> {
					x.setCoolnessFactor(100);
					return x;
				}).findFirst().orElse(new User("-", "-"));

		assertThat("pilar", equalTo(coolestUser.getName()));
		assertThat(100, equalTo(coolestUser.getCoolnessFactor()));
		// Option Some None
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

		assertThat(new int[] { 2, 4, 6, 8, 10 }, equalTo(result));
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
		int count = integer.parallelStream().mapToInt(x -> {
			x = x * 2;
			System.out.println(x);
			return x;
		}).reduce((x, y) -> x + y).getAsInt();

		assertThat(60, equalTo(count));
	}

}
