package com.dollop.app.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.dollop.app.Exception.UserNotFoundException;

@RestControllerAdvice
public class MyCustomExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> showCustomErrorMsg(UserNotFoundException snfe){
		
		
		return new ResponseEntity<String>(snfe.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
