package com.my.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// Exception용 클래스
public class ModifyException extends Exception {

	public ModifyException(String message) {
		super(message);
	}

} // end class
