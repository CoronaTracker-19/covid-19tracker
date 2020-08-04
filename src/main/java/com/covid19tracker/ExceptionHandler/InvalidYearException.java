package com.covid19tracker.ExceptionHandler;

public class InvalidYearException extends RuntimeException{

	public InvalidYearException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidYearException(String message) {
		super(message);
	}

	public InvalidYearException(Throwable cause) {
		super(cause);
	}
	

}
