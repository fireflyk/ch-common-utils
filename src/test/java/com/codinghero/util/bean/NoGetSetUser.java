package com.codinghero.util.bean;

public class NoGetSetUser {
	private String name;
	
	public NoGetSetUser() {
	}
	
	public NoGetSetUser(String name) {
		this.name = name;
	}
	
	public String specialGet() {
		return name;
	}
}
