package com.covid19tracker.ExceptionHandler;

import java.text.ParseException;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@ControllerAdvice
public class HandlerConfig {
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleExternalServerDownException(ExternalServerDownException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Please try after sometime");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleCountryNotFoundException(CountryNotFoundException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Please enter a valid country name");
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleGenericJDBCException(GenericJDBCException e)
	{
		
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Please enter a valid date");
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleGenericJDBCException(ParseException e)
	{
		
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Please enter a valid date");
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidCountryNumberException(InvalidCountryNumberException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Enter size less than equal to 215 and greater than 0 and number less than equal to 215 and greater than or equal to 0");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Enter a valid parameter");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
	}
//	@ExceptionHandler
//	public ResponseEntity<ErrorResponse> handleException(Exception e)
//	{
//		ErrorResponse error=new ErrorResponse();
//		
//		error.setStatus(HttpStatus.BAD_REQUEST.value());
//		error.setMessage(e.getMessage());
//		error.setDetails("Try again");
//		error.setTimestamp(System.currentTimeMillis());
//		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
//	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidOrderException(InvalidOrderException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Put a valid order type");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidMonthException(InvalidMonthException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Put a valid month");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidYearException(InvalidYearException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Put a valid year");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		error.setMessage(e.getMessage());
		error.setDetails("Put parameters that has corresponding data present");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleInvalidWeekNoException(InvalidWeekNoException e)
	{
		ErrorResponse error=new ErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setDetails("put a valid week number");
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_ACCEPTABLE);
	}
}
