package com.my.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// Exception용 클래스
public class RemoveException extends Exception {

	public RemoveException(String message) {
		super(message);
	}

} // end class
