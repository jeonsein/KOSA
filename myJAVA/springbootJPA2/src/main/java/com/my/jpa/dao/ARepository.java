package com.my.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.my.jpa.entity.A;

public interface ARepository extends CrudRepository<A, String> {
	//쿼리메서드작성 가능 : findBy멤버변수명, 반환형은 List자료형
	List<A> findByA4(String a4);
	
	//JPQL문법을 사용
//	@Query("SELECT p FROM 엔티티클래스 AS p WHERE 멤버변수 LIKE %:word%")
	@Query(value="SELECT * FROM 테이블", nativeQuery = true)
    public List<A> findByName(
    		@org.springframework.data.repository.query.Param("word") String name);
	
} // end interface