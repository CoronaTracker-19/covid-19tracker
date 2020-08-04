package com.covid19tracker.ExceptionHandler;

public class ErrorResponse {
	private int status;
	private String message;
	private String details;
	private long timestamp;
	
	public ErrorResponse() {
		
	}
	
	public ErrorResponse(int status, String message, String details, long timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	

}
