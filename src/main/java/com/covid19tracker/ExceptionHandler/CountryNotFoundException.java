package com.covid19tracker.ExceptionHandler;

public class CountryNotFoundException extends RuntimeException {

	

	public CountryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CountryNotFoundException(String message) {
		super(message);
	}

	public CountryNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
