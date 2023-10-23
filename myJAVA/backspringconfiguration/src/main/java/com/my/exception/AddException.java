package com.my.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// Exception용 클래스
public class AddException extends Exception {

	public AddException(String message) {
		super(message);
	}

} // end class
