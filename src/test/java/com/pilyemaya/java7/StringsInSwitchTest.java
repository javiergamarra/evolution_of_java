package com.pilyemaya.java7;

import org.junit.Assert;
import org.junit.Test;

public class StringsInSwitchTest {

	@Test
	public void getRowColorTest() {
		Assert.assertEquals("red", getRowColorInOlderJava("no data"));
		Assert.assertEquals("red", getRowColorInJava7("no data"));
	}

	public String getRowColorInOlderJava(final String state) {
		if ("correct".equals(state)) {
			return "green";
		} else if ("incorrect".equals(state) || "no data".equals(state)) {
			return "red";
		} else if ("incomplete".equals(state)) {
			return "yellow";
		} else {
			return "white";
		}
	}

	public String getRowColorInJava7(final String state) {
		switch (state) {
		case "correct":
			return "green";
		case "incorrect":
		case "no data":
			return "red";
		case "incomplete":
			return "yellow";
		default:
			return "white";
		}
	}
}
