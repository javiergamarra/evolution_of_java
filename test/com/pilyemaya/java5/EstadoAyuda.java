package com.pilyemaya.java5;

import java.util.Arrays;
import java.util.List;

public enum EstadoAyuda {

	ANULADA("Ayuda anulada","BROWN"), CONCEDIDA("Ayuda concedida","GREEN")
	, DENEGADA("Ayuda denegada","RED"), CON_INCIDENCIA("Ayuda con incidencia","YELLOW");

	private String nombre;
	private String color;


	private EstadoAyuda(final String nombre, final String color) {
		this.nombre = nombre;
		this.color=color;
	}


	public String getNombre() {
		return nombre;
	}

	public String getColor() {
		return color;
	}

	public static List<EstadoAyuda> obtenerValoresEstadoAyuda() {
		return Arrays.asList(ANULADA, CONCEDIDA, DENEGADA, CON_INCIDENCIA);
	}

}
