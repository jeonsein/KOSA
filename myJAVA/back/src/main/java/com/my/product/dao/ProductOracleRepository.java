package com.my.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class ProductOracleRepository implements ProductRepository {

	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} // try-cahtch
		
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
			rs =pstmt.executeQuery();
			
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
		
	} // selectAll()
	
	@Override
	public int selectCount() throws FindException {
		
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
		
	} // selectCount()
	
//	-------------------------------------------------------------------
	
	// Test를 위해서 main문 작성(톰캣과는 무관하고 main()으로 테스트를 함)
	public static void main(String[] args) {
		ProductOracleRepository repository = new ProductOracleRepository();
		
		/*
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
		
		try {
			System.out.println(repository.selectCount());
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch
		
	} // end main

} // end class
