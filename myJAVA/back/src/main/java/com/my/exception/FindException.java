package com.my.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// Exception용 클래스
public class FindException extends Exception {

	public FindException(String message) {
		super(message);
	}

} // end class
