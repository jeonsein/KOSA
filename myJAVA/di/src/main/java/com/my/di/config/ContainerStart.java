package com.my.di.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.di.dto.A;
import com.my.di.dto.B;
import com.my.exception.FindException;
import com.my.product.dao.ProductRepository;
import com.my.product.service.ProductService;

public class ContainerStart {

	public static void main(String[] args) {

		String configFileName = "myApplicationContext.xml";
		
		// 스프링 컨테이너를 구동하는 코드
		ApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext(configFileName);
		
		// ----------------------------
		
		// 스프링 컨테이너에 있는 스프링 객체를 찾는다
		A a1 = ctx.getBean("a", com.my.di.dto.A.class);	// 첫번째 인자 = bean 태그에 등록되어 있는 id 속성값
														// 두번째 인자 = bean 태그에 등록된 클래스의 자료형
		System.out.println("a1: " + a1);
		
		A a2 = ctx.getBean("a",com.my.di.dto.A.class);
		System.out.println("a2: " + a2);
		
		System.out.println("싱글톤 여부: " + (a1 == a2));
		System.out.println("msg: " + a1.getMsg());
		
		// -----------------------------
		
		B b = ctx.getBean("b", com.my.di.dto.B.class);
		
		System.out.println("b.getNo(): " + b.getNo());
		
		// -----------------------------
		
		ProductRepository r1 = 
				ctx.getBean("productDAO", com.my.product.dao.ProductRepository.class);
		System.out.println(r1);
		
		ProductService s1 = 
				ctx.getBean("productService", com.my.product.service.ProductService.class);
		
		try {
			System.out.println(s1.findAll(1));
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		
	} // end main

} // end class
