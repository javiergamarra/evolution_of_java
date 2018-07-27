package com.pilyemaya.java5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ForeachTest {
	private static final String RESULT_OK = "Value 1-Value 2-Value 3-";
	private final List<String> values = new ArrayList<String>();

	@Before
	public void setUp() {
		fillValues();
	}

	@Test
	public void foreachInOlderJavaTest() {
		String result = "";
		for (int i = 0; i < values.size(); i++) {
			result += values.get(i) + "-";
		}
		Assert.assertEquals(RESULT_OK, result);
	}

	@Test
	public void foreachInJava5Test() {
		String result = "";
		for (final String value : values) {
			result += value + "-";
		}
		Assert.assertEquals(RESULT_OK, result);
	}

	private void fillValues() {
		values.clear();
		values.add("Value 1");
		values.add("Value 2");
		values.add("Value 3");
	}
}
