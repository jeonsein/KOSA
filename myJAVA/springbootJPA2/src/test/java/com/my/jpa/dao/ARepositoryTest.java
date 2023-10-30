package com.my.jpa.dao;

import java.math.BigDecimal;
import java.util.List;
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
	void test() {
		// r 객체 확인
		// 의존성을 주입받은 ARepository의 실제 구현체가 자동 제공됨
		log.error(r.getClass().getName());
		
	} // test()
	
	// ------------------
	
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
	
	// ------------------
	
	@Test
	void test5QueryMethod() {
		
		// 모든 행을 객체의 리스트로 반환
		// r.findAll();
		
		// 기본제공되는 findById()와 findAll()가 아닌 직접 메소드 만들어서 사용 가능
		// ARepository의 선언부에 선언하기
		List<A> list = r.findByA4("a4one");
		
		// log.error(list); => 오류남. list 자료형이기 때문에 아래처럼 포맷팅 해줘야함
		log.error("r.findByA4(a4one) 결과: {}", list);
		
	} // test5QueryMethod()
	
	// ------------------
	
	@Test
	void test6FindByA4Like() {
		String word = "%4f%";
		
		List<A> list = r.findByA4Like(word);
		log.error("r.findByA4Like(4f)의 결과: {}", list);
		
	} // test6FindByA4Like(0
	
	// ------------------
	
	@Test
	void test7FindByA4LikeOrderByA4() {
		String word = "%4f%";
		
		List<A> list = r.findByA4LikeOrderByA4(word);
		log.error("r.findByA4LikeOrderByA4(4f)의 결과: {}", list);
	} // test7FindByA4LikeOrderByA4()
	
	// ------------------
	
	@Test
	void test8FindByA4LikeJpql() {
		String word = "%4f%";
		
		List<A> list = r.findByA4LikeJpql(word);
		log.error("r.test8FindByA4LikeJpql(4f)의 결과: {}", list);
	} // test8FindByA4LikeJpql()
	
	// ------------------
	
	@Test
	void test9FindByA4LikeJpqlA1A4() {
		String word = "%4f%";
		
		List<Object[]> list = r.findByA4LikeJpqlA1A4(word);
		
		for(Object[] arr : list) {
			log.error("r.findByA4LikeJpqlA1A4(4f)의 결과: {}, {}", arr[0], arr[1]);
		} // enhanced for()
	} // test9FindByA4LikeJpqlA1A4()
	
	// ------------------
	
	@Test
	@Transactional
	@Commit
	void test10Modify() {
		String a_1 = "one";
//		BigDecimal a_2 = new BigDecimal(999);
		BigDecimal a_2 = new BigDecimal(888);
		
//		r.modify(a_1, a_2);

		int rowcnt = r.modify(a_1, a_2);
		log.error("수정된 행 수 : {}", rowcnt);

	} // test10Modify()
	
} // end class