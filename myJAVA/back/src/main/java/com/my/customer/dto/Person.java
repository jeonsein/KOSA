package com.my.customer.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String address;
	
	public Person() {
		super();
	}	
	
	public Person(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	
} // end class
