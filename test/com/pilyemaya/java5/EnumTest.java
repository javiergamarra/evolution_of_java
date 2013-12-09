package com.pilyemaya.java5;

import org.junit.Assert;
import org.junit.Test;

public class EnumTest {

	private EstadoAyuda estadoAyuda;

	@Test
	public void estadoActivoTest() {
		estadoAyuda=EstadoAyuda.CONCEDIDA;
		Assert.assertEquals("GREEN", estadoAyuda.getColor());
	}

}
