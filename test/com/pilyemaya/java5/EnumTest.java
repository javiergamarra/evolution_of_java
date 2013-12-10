package com.pilyemaya.java5;

import org.junit.Assert;
import org.junit.Test;

public class EnumTest {

	private State state;

	@Test
	public void correctStateTest() {
		state=State.CORRECT;
		Assert.assertEquals("GREEN", state.getColor());
	}

}
