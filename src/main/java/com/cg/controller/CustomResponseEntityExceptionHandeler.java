/**
 * 
 */
package com.cg.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.exception.ExceptionResponse;
import com.cg.exception.UserAlreadyExistException;

/**
 * @author sohel
 *
 */

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandeler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ IllegalArgumentException.class })
	public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest req) {
		ExceptionResponse expResp = new ExceptionResponse(new Date(), ex.getMessage(), "Some data is missing");
		return new ResponseEntity(expResp, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({ UserAlreadyExistException.class })
	public final ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest req) {
		ExceptionResponse expResp = new ExceptionResponse(new Date(), ex.getMessage(), "User Already Exist");
		return new ResponseEntity(expResp, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
		ExceptionResponse expResp = new ExceptionResponse(new Date(), ex.getMessage(), "Server down");
		return new ResponseEntity(expResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * @ExceptionHandler({ NoValuePresentException.class }) public final
	 * ResponseEntity<Object> handleUserNotFoundException(NoValuePresentException
	 * ex, WebRequest req) { ExceptionResponse expResp = new ExceptionResponse(new
	 * Date(), ex.getMessage(), "Cart is empty"); return new ResponseEntity(expResp,
	 * HttpStatus.NOT_FOUND); }
	 * 
	 * @ExceptionHandler({ FeedbackNotFoundException.class }) public final
	 * ResponseEntity<Object> handleUserNotFoundException(FeedbackNotFoundException
	 * ex, WebRequest req) { ExceptionResponse expResp = new ExceptionResponse(new
	 * Date(), ex.getMessage(), "Feedback not present"); return new
	 * ResponseEntity(expResp, HttpStatus.NOT_FOUND); }
	 * 
	 * @ExceptionHandler({ CustomerNotFoundException.class }) public final
	 * ResponseEntity<Object> handleUserNotFoundException(CustomerNotFoundException
	 * ex, WebRequest req) { ExceptionResponse expResp = new ExceptionResponse(new
	 * Date(), ex.getMessage(), "Customer not present"); return new
	 * ResponseEntity(expResp, HttpStatus.NOT_FOUND); }
	 */

}
