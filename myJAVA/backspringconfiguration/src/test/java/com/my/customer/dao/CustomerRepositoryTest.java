package com.my.customer.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.customer.dto.Customer;
import com.my.exception.FindException;

@ExtendWith(SpringExtension.class) // 스프링용 단위테스트
//해당 클래스나 설정 xml 파일을 이용해서 스프링 컨테이너를 구동함
//ApplicationContext = 스프링 컨테이너 = 스프링 엔진 생성
@ContextConfiguration(classes = {config.MyApplicationContext.class})
class CustomerRepositoryTest {
	
	// 스프링 컨테이너에서 관리되는 객체를 찾아와야함!
	@Autowired
	CustomerRepository repository;
	
	@Test
	void test() {
		int i = 10;	// 실제 처리결과 데이터
		int expectedI = 10; // 성공될 예상 데이터
		assertTrue(i == expectedI); // 성공
		
		String msg = "hello";
		String expectedMsg = "Hi";
		assertEquals(msg, expectedMsg); // 실패
	} // test()
	
	@Test
	void testInsert() {
		
	}

	@Test
	@DisplayName("아이디로 고객 검색 테스트")
	void testSelectById() throws FindException {
		String id = "010";
		String expectedPwd = "1";
		String expectedName = "1";
		
		Customer c = repository.selectById(id);
		
		assertEquals(expectedPwd, c.getPwd());
		assertEquals(expectedName, c.getName());
	} // testSelectById()
	
} // end class
