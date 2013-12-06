package com.pilyemaya.java7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DiamondOperatorTest {

	@Test
	public void createMapOfValuesTest() throws IOException {
		final Map<Integer, List<String>> map1 = createMapOfValuesInOlderJava(0,
				1, 2, 3);
		final Map<Integer, List<String>> map2 = createMapOfValuesInJava7(0, 1,
				2, 3);
		Assert.assertEquals(getListOfValuesInOlderJava(), map1.get(0));
		Assert.assertEquals(getListOfValuesInJava7(), map2.get(0));
	}

	private Map<Integer, List<String>> createMapOfValuesInOlderJava(
			final int... keys) {

		final Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		for (final int key : keys) {
			map.put(key, getListOfValuesInOlderJava());
		}
		return map;

	}

	private ArrayList<String> getListOfValuesInOlderJava() {
		return new ArrayList<String>(Arrays.asList("value1", "value2"));
	}

	private Map<Integer, List<String>> createMapOfValuesInJava7(
			final int... keys) {
		final Map<Integer, List<String>> map = new HashMap<>();
		for (final int key : keys) {
			map.put(key, getListOfValuesInJava7());
		}
		return map;

	}

	private ArrayList<String> getListOfValuesInJava7() {
		return new ArrayList<>(Arrays.asList("value1", "value2"));
	}
}
