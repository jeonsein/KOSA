package com.my.jpa.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.A;

@SpringBootTest
class JPATest {

	@PersistenceContext
	EntityManager em;
	
	@Test
	@Transactional
	@Commit
	void test() {
		A a = new A();
		
		a.setA_1("two");
		a.setA_2(new BigDecimal(2));

		em.persist(a);
		em.flush(); // DB에 flush()로 자료 전송
		em.clear(); // detached 상태로 빠트리기
		
	} // test()

} // end class
