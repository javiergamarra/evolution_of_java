package com.nhpatt.java8.lambdas;

public class User {

	private String name;

	private String surname;

	private Integer coolnessFactor;

	public User(String name, String surname) {
		this(name, surname, 0);
	}

	public User(String name, String surname, Integer coolnessFactor) {
		this.name = name;
		this.surname = surname;
		this.coolnessFactor = coolnessFactor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getCoolnessFactor() {
		return coolnessFactor;
	}

	public void setCoolnessFactor(Integer coolnessFactor) {
		this.coolnessFactor = coolnessFactor;
	}

}
