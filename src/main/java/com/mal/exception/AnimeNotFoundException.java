package com.mal.exception;

public class AnimeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public AnimeNotFoundException(String message) {
	this.message=message;
	}
	public String getMessage() {
		return message;
	}
}
