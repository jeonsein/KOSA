package com.my.customer.dto;

import java.io.Serializable;

public class Person implements Serializable {
	protected String name;
	protected String address;
	
	public Person() {
		super(); // Object의 매개변수 없는 생성자를 호출!
	}
	
	public Person(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
} // end class
