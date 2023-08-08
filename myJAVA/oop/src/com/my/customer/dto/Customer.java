package com.my.customer.dto;

import java.io.Serializable;

// 부모 클래스로부터 상속 먼저! 그 이후 구현!
public class Customer extends Person implements Serializable {

	private String id;
	private String pwd;
	
	public Customer() {
		super();
	}
	
	public Customer(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public Customer(String id, String pwd, String name, String address) {
//		this.id = id;
//		this.pwd = pwd;
//		🔽
		this(id, pwd);
		this.name = name;
		super.address = address;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String toString() {
		return "Customer [id=" + id + ", pwd=" + pwd + ", name=" + name + ", address=" + address + "]";
	}
	
} // end class
