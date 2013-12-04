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
		if (state.equals("correct")) {
			return "green";
		} else if (state.equals("incorrect") || state.equals("no data")) {
			return "red";
		} else if (state.equals("incomplete")) {
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
