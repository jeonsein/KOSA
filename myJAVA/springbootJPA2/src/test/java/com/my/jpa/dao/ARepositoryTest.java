package com.my.jpa.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.A;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class ARepositoryTest {

	@Autowired ARepository r;
	
	@Test
	@DisplayName("test1Save()")
	@Transactional
	@Commit
	void test1Save() {
		A a = new A();
		
		a.setA_1("one");
		a.setA_2(new BigDecimal(1.0));
		a.setA4("a4");
		
		r.save(a);
		
		// -----
		
		A a1 = new A();
		
		a1.setA_1("one");
		a1.setA_2(new BigDecimal(2.0));
		
		r.save(a1);
		
	} // test1Save()
	
	// ------------------
	
	@Test
	void test2FindById() {
		
		String a_1 = "one";
		
		Optional<A> optA = r.findById(a_1);
		
		Assertions.assertTrue(optA.isPresent());
		A a = optA.get();
		
		log.error("a_1: {}, a_2: {}", a.getA_1(), a.getA_2());
		
	} // test2FindById()
	
	// ------------------
	
	@Test
	@DisplayName("UPDATE용도의 save() 테스트")
	void test3Save() {
		
		String a_1 = "one";
		
		Optional<A> optA = r.findById(a_1);
		
		Assertions.assertTrue(optA.isPresent());
		A a = optA.get();
		
		a.setA_2(new BigDecimal(3.0));
		
		r.save(a);
		
	} // test3Save()
	
	// ------------------
	
	/*
	@Test
	@Transactional
	@Commit
	void test4Delete() {
		String a_1 = "one";
//		r.deleteById(a_1); // -> entity가 없으면 silently ignore -> rollback 예외 발생
		
		// -----
		
		A a = new A();
		
		a.setA_1(a_1);
		r.delete(a); // -> rollback 예외 발생 안함
	} // test4Delete()
	*/ 
	// DELETE 수행할 때에는 아래처럼 해주기!!
	
	// 방법 #1_deleteById()
	@Test
	@Transactional
	@Commit
	void test4DeleteById() {
		String a_1 = "one";
		
		r.deleteById(a_1); // -> entity가 없으면 silently ignore -> rollback 예외 발생
	} // test4DeleteById()
	
	// 방법 #2_delete()
	@Test
	@Transactional
	@Commit
	void test4Delete() {
		String a_1 = "one";

		Optional<A> aOpt = r.findById(a_1);
		
		// aOpt 있는 경우에만 delete() 수행
		aOpt.ifPresent(a -> r.delete(a));
		
	} // test4Delete()

} // end class