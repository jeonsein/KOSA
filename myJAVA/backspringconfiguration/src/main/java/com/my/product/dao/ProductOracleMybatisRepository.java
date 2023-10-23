package com.my.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.exception.FindException;
import com.my.product.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository(value = "productDAO")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductOracleMybatisRepository implements ProductRepository {

	@Autowired
	@Qualifier(value = "sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {
		
		SqlSession session = null;
		
		List<Product> list = new ArrayList<>();
		
		try {
			session = sqlSessionFactory.openSession();
			
			Map<String, Integer> map = new HashMap<>();
			map.put("start", startRow);
			map.put("end", endRow);
			
			// 위에서 선언한 list에 값 담아주기
			list = session.selectList("com.my.product.ProductMapper.selectAll", map);
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			
			if(session != null) {
				session.close();
			} // if
			
		} // try-catch-finally
		
	} // selectAll()
	
	@Override
	public int selectCount() throws FindException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			int count = session.selectOne("com.my.product.ProductMapper.selectCount");

			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			
			if(session != null) {
				session.close();
			} // if
			
		} // try-catch-finally
		
	} // selectCount()

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		
		SqlSession session = null;
		
		try {
			
			session = sqlSessionFactory.openSession();
			Product p = session.selectOne("com.my.product.ProductMapper.selectByProdNo", prodNo);
			
			if(p != null) {
				return p;
			} else {
				throw new FindException("상품이 없습니다.");
			} // if-else
			
		} catch(Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			
			if(session != null) {
				session.close();
			} // if
			
		} // try-catch-finally
		
	} // selectByProdNo()
	
//	-------------------------------------------------------------------

	// Test를 위해서 main문 작성(톰캣과는 무관하고 main()으로 테스트를 함)
	public static void main(String[] args) {
		ProductOracleMybatisRepository repository = new ProductOracleMybatisRepository();

		try {
//			Product p = repository.selectByProdNo("F0001");
//			System.out.println(p);
			
			System.out.println(repository.selectByProdNo("F0001"));
//			log.info(repository.selectByProdNo("F0001")); // 존재 O
//			log.info(repository.selectByProdNo("F0012")); // 존재 X
			
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		
	} // end main

} // end class
