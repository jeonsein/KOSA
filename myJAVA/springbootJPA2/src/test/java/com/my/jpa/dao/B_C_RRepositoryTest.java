package com.my.jpa.dao;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.my.jpa.entity.B;
import com.my.jpa.entity.C;
import com.my.jpa.entity.R;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class B_C_RRepositoryTest {
	
	@Autowired BRepository br;
	@Autowired CRepository cr;
	@Autowired RRepository rr;

	// --------------------------
	
	@Test
	@Transactional
	@Commit
	void test1C_Save() {
		for(int i=1; i<=5; i++) {
			
//			C c = new C("id"+i, "n"+i);
			C c = C.builder()
					.cId("id"+i)
					.cName("n"+i)
					.build();
			
			cr.save(c);
			
		} // for
	} //test1C_Save()
	
	// --------------------------
	
	@Test
	@Transactional
	@Commit
	void test2B_Save() {
		Optional<C> optC = cr.findById("id1");
		Assertions.assertTrue(optC.isPresent());
		
		C c= optC.get();
		for(int i=1; i<=2; i++) {
			B b = new B();
			b.setBC(c); //게시글작성자
			br.save(b);
		}
	} // test2B_Save()
	
	// --------------------------
	
	@Test
	@Transactional
	@Commit
	void test3B_Find() {
		br.findAll();
		Iterable<B> it = br.findAll();
		
		log.error("br.findAll(): {}" + it);
	} // test3B_Find()
	
	// --------------------------
	
	@Test
	@Transactional
	@Commit
	void test4R_Save() {
		for(long no = 2L; no <= 4L; no++) {
			
			R r = new R();
			
			r.setRNo(no);	//댓글번호
			r.setRContent("댓글" + no);
			r.setBNo(1L);	//1번글에대한 댓글
			
			rr.save(r);
		} // for

	} // test4R_Save()
	
	// --------------------------
	
	@Test
	@Transactional
	@Commit
	void test5R_DeleteById() {
		br.deleteById(1L);
	} // test5R_DeleteById()
	
	// --------------------------
	
	@Test
	@Transactional
	@Commit
	void test6C_findById() {
		Optional<C> optC = cr.findById("id1");
		
		Assertions.assertTrue( optC.isPresent() );
		C c = optC.get();
		
//		log.error("회원 정보: {}", c);
		log.error("회원 정보이름: {}, {}", c.getCName(), c.getBs());
		
	} // test6C_findById()

} // end class