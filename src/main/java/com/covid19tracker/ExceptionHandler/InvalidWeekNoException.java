package com.covid19tracker.ExceptionHandler;

public class InvalidWeekNoException extends RuntimeException{

	public InvalidWeekNoException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidWeekNoException(String message) {
		super(message);
	}

	public InvalidWeekNoException(Throwable cause) {
		super(cause);
	}

}
