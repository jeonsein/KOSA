package com.my.jpa.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.my.jpa.entity.A;

//public interface ARepository extends CrudRepository<A, String> {
public interface ARepository extends JpaRepository<A, String> { // 이렇게 해도 됨!
	
	// 쿼리 메소드 작성 가능 : findBy멤버변수명, 반환형은 List자료형
	// 쿼리메소드 = find~ 메소드
	List<A> findByA4(String a4); // a4 이름에 만족하는 행들만 검색할 수 있는 메소드
	
	/**
	 * 단어를 포함한 a4 멤버 변수를 갖는 엔티티 객체들을 반환한다
	 * @param word 단어
	 * @return
	 */
	List<A> findByA4Like(String word); 
	// SELECT FROM WHERE a4 LIKE '%?%'
	
	List<A> findByA4LikeOrderByA4(String word);
	// SELECT FROM WHERE a4 LIKE '%?%' ORDER BY a4
	
	// JPQL문법을 사용
//	@Query("SELECT p FROM 엔티티클래스 AS p WHERE 멤버변수 LIKE %:word%")
//	@Query(value="SELECT * FROM 테이블", nativeQuery = true)
//    public List<A> findByName(
//    		@org.springframework.data.repository.query.Param("word") String name);
	
	// JPQL문법을 사용
	// JPQL에서는 * 사용할 수 없기 때문에 별칭을 주어서 SELECT 별칭으로 SELECT *의 효과를 냄
	// @Query("SELECT a FROM A AS a WHERE a4 LIKE %:word%")
	// public List<A> findByA4LikeJpql(String word);
	
	// Native Query 사용
	@Query(value = "SELECT * FROM a_tbl WHERE a4 LIKE :word",
			nativeQuery = true)
	public List<A> findByA4LikeJpql(String word);
	
	// 특정 컬럼만 검색해서 가져오는 메소드
	// return 자료형이 List<A>가 불가능하기 때문에 Object 배열 타입으로 해주기
	@Query(value = "SELECT a_1, a4 FROM a_tbl WHERE a4 LIKE :word",
			nativeQuery = true)
	public List<Object[]> findByA4LikeJpqlA1A4(String word);
	
	@Modifying
	@Query(value = "UPDATE a_tbl SET a_2 = :a_2 WHERE a_1 = :a_1",
			nativeQuery = true)
	// public void modify(String a_1, BigDecimal a_2);
	public int modify(String a_1, BigDecimal a_2);
	
} // end interface