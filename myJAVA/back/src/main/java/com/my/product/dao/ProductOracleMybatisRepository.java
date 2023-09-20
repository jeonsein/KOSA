package com.my.product.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ProductOracleMybatisRepository implements ProductRepository {

	private SqlSessionFactory sqlSessionFactory;
	
	public ProductOracleMybatisRepository() {
		String resource = "com/my/sql/mybatis-config.xml";
		InputStream inputStream;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		} // try-catch
	
	} // constructor
	
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
			
			/*
			selectOne()
    		-> select SQL구문 실행했을 때 예상되는 실행 결과행이 최대 한 개면 사용
    		
    		selectList()
			-> select SQL구문 실행했을 때 예상되는 실행 결과행이 여러개면 사용
			 */
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			
			if(session != null) {
				session.close();
			} // if
			
		} // try-catch-finally
		
		/* MyBatis로 변경 전
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} // try-catch
		
		String selectAllSQL = "SELECT *\r\n"
				+ "		FROM ( SELECT rownum rn, a.*\r\n"
				+ "		           FROM (SELECT *\r\n"
				+ "		                 FROM product\r\n"
				+ "		                 ORDER BY prod_no\r\n"
				+ "		                ) a\r\n"
				+ "		      )\r\n"
				+ "		WHERE rn BETWEEN ? AND ?";
		
		List<Product> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(selectAllSQL);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String prodNo = rs.getString("PROD_NO");
				String prodName = rs.getString("PROD_NAME");
				int prodPrice = rs.getInt("PROD_PRICE");
				
				Product p = new Product(prodNo, prodName, prodPrice);
				list.add(p);
			} // while
			return list; // return 직전에 finally 수행!
		} catch (SQLException e) {
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		} // // try-catch-finally
		*/
		
	} // selectAll()
	
	@Override
	public int selectCount() throws FindException {
		
		SqlSession session = null;
		
		try {
			session = sqlSessionFactory.openSession();
			int count = session.selectOne("com.my.product.ProductMapper.selectCount");
			// .selectOne() = pstmt.executeQuery()
			// .selectList() = rs = pstmt.executeQuery();
			//					if(rs.next()) {
			//						rs.get("~");
			//					} else {
			//						return ~;
			//					}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			
			if(session != null) {
				session.close();
			} // if
			
		} // try-catch-finally
		
		/* MyBatis로 변경 전
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException();
		} // try-catch
		
		String selectCouneSQL = "SELECT COUNT(*)\r\n"
				+ "FROM product";
		
		try {
			pstmt = conn.prepareStatement(selectCouneSQL);
			rs = pstmt.executeQuery();
			
			// COUNT(*)함수로 결과행 반환은 0일지라도 0의 값을 받는 행을 반환함 
			// -> 무조건 true값
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}	finally {
			MyConnection.close(conn, pstmt, rs);
		} // try-catch-finally
		*/
		
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
		
		/* MyBatis로 변경 전
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} // try-catch
		
		String selectByProdNoSQL = "SELECT *\r\n"
				+ "FROM product\r\n"
				+ "WHERE prod_no=?";
		
		// 상품 번호에 맞는 상품은 하나이거나 혹은 없거나
		try {
			pstmt = conn.prepareStatement(selectByProdNoSQL);
			pstmt.setString(1, prodNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	// 상품 번호에 맞는 상품이 존재
				return new Product( rs.getString("prod_no"),
									rs.getString("prod_name"),
									rs.getInt("prod_price")
								  );
			} else {		// 상품 번호에 맞는 상품이 존재하지 않음
				throw new FindException("상품이 없습니다.");
			} // if-else
			
		} catch (SQLException e) {
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		} // try-catch-finally
		*/
		
	} // selectByProdNo()
	
//	-------------------------------------------------------------------

	// Test를 위해서 main문 작성(톰캣과는 무관하고 main()으로 테스트를 함)
	public static void main(String[] args) {
		ProductOracleMybatisRepository repository = new ProductOracleMybatisRepository();
		
		/* 
		// test#1
		int startRow = 2;
		int endRow = 4;
		
		try {
			List<Product> list = repository.selectAll(startRow, endRow);
			log.info(list);
//			System.out.println(list);
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		*/
		
		/*
		// test#2
		try {
			System.out.println(repository.selectCount());
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		*/
		
		// test#3
		try {
//			Product p = repository.selectByProdNo("F0001");
//			System.out.println(p);
			
			log.info(repository.selectByProdNo("F0001")); // 존재 O
//			log.info(repository.selectByProdNo("F0012")); // 존재 X
			
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		
		
	} // end main

} // end class
