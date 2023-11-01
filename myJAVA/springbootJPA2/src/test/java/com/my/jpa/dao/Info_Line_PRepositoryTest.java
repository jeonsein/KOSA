package com.my.jpa.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.Info;
import com.my.jpa.entity.Line;
import com.my.jpa.entity.P;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Info_Line_PRepositoryTest {

	@Autowired
	InfoRepository infoR;
	
	@Test
	void test1Info_save() {
		Info info = new Info();
		
		info.setInfoId("id1");	// 주문자 아이디
		info.setInfoDt(new Date(new java.util.Date().getTime())); 
		// -> Long 타입으로 반환
		
		List<Line> lines = new ArrayList<>();
		
		// 주문 상세에 여러 주문이 저장될 수 있도록 반복문 사용
		for(int i = 1; i <= 2; i++) {
			
			Line line = new Line(); // 상세
			line.setLineQuantity(i);
			line.setInfo(info);	// One의 정보를 Many쪽에 세팅해주어야 함!
			// -> 자식쪽에서 부모와 연결
			
			P p = new P();
			p.setPNo("C"+i);
			
			line.setLineP(p);
			
			lines.add(line);
		} // for
		
		info.setLines(lines);	// -> 부모쪽에서 자식과 연결
		
		infoR.save(info);
		
	} // test1Info_save()
	
	// ----------------------------------------------------
	
	@Test
	void test2Info_findById() {
		
		Long infoNo = 1L;
		
		// PK에서 Entity Manager가 식별하는 것
		Optional<Info> optInfo = infoR.findById(infoNo);
		
		Assertions.assertTrue(optInfo.isPresent());
		
		Info info = optInfo.get();
		log.error("주문자 아이디: {}", info.getInfoId());
		log.error("주문자 일자: {}", info.getInfoDt());
		log.error("주문 상세: {}", info.getLines());
		
	} // test2Info_findById()
	
	// ----------------------------------------------------
	
	@Test
	@Transactional
	@Commit
	void test3Info_deleteById() {
		
		Long infoNo = 1L;
		
		infoR.deleteById(infoNo);
		
	} // test3Info_deleteById()
	
} // end class
