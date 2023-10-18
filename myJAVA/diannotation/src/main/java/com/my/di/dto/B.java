package com.my.di.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Component
@Component(value="b")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class B {
	
	private int no;
	@Autowired
	private A a;
	
} // end class