package com.my.board.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ValidTestControllerAdvice {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintViodationExceptionHandler(
			ConstraintViolationException e) {
		String msg = e.getMessage();
		
		log.error("요청전달데이터 유효성검사 실패: {}" , msg);
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<>(msg, headers, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> BindExceptionHandler(
			BindException e) {
		BindingResult br = e.getBindingResult();
		ObjectError error = br.getAllErrors().get(0);
		String msg = error.getDefaultMessage();
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return new ResponseEntity<>(msg, headers, HttpStatus.BAD_REQUEST);
	}
	
} // end class