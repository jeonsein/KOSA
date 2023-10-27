package com.my.board.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@RestControllerAdvice
public class MyBoardControllerAdvice {
	
	/*
	@ExceptionHandler(AddException.class)
	public ResponseEntity<?> addException(AddException e){		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ModifyException.class)
	public ResponseEntity<?> modifyException(AddException e){		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RemoveException.class)
	public ResponseEntity<?> removeException(AddException e){		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	*/
	
	@ExceptionHandler(value= {AddException.class,
							  ModifyException.class,
							  RemoveException.class,
							  FindException.class})
	public ResponseEntity<?> exceptionHandler(Exception e){		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
