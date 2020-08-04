package com.covid19tracker.ExceptionHandler;

public class ExternalServerDownException extends RuntimeException {

	public ExternalServerDownException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExternalServerDownException(String message) {
		super(message);
	}

	public ExternalServerDownException(Throwable cause) {
		super(cause);
	}
}
