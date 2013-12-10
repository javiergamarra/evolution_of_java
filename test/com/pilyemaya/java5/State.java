package com.pilyemaya.java5;

import java.util.Arrays;
import java.util.List;

public enum State {

	INCOMPLETE("Incomplete state","YELLOW"), CORRECT("Correct state","GREEN")
	, INCORRECT("Incorrect State","RED");

	private String name;
	private String color;


	private State(final String name, final String color) {
		this.name = name;
		this.color=color;
	}


	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public static List<State> getStateValues() {
		return Arrays.asList(INCOMPLETE,CORRECT, INCORRECT);
	}

}
