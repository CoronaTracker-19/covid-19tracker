package com.covid19tracker.ExceptionHandler;

public class InvalidMonthException extends RuntimeException{

	public InvalidMonthException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidMonthException(String message) {
		super(message);
	}

	public InvalidMonthException(Throwable cause) {
		super(cause);
	}
	

}
