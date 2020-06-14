package com.gst.users.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.gst.users.model.response.ErrorMessage;

@ControllerAdvice
public class GenericExceptionHandler {


	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> forAllExcpeions(Exception ex, WebRequest request) {
		
		ErrorMessage errorMessage  = new ErrorMessage(new Date() , ex.getMessage() );
		
		return new ResponseEntity<>(errorMessage , new HttpHeaders(), HttpStatus.BAD_REQUEST);
		
	}
	
}
