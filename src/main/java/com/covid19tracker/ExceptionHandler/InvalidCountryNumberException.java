package com.covid19tracker.ExceptionHandler;

public class InvalidCountryNumberException extends RuntimeException{

	public InvalidCountryNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCountryNumberException(String message) {
		super(message);
	}

	public InvalidCountryNumberException(Throwable cause) {
		super(cause);
	}
	
	

}
