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

public class ProductOracleRepository implements ProductRepository {
	@Override
	public List<Product> selectAll(int startRow, int endRow) throws FindException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
//			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
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
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
	}
	
	public int selectCount() throws FindException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}
		
		String selectCountSQL = "SELECT COUNT(*)\r\n"
				+ "FROM product";
		try {
			pstmt = conn.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}		
	}
	
	
	

	@Override
	public Product selectByProdNo(String prodNo) throws FindException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}
		String selectByProdNoSQL = "SELECT *\r\n"
				+ "		 FROM product\r\n"
				+ "		 WHERE prod_no=?";
		try {
			pstmt = conn.prepareStatement(selectByProdNoSQL);
			pstmt.setString(1, prodNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Product(rs.getString("prod_no"),
						           rs.getString("prod_name"),
						           rs.getInt("prod_price")
						);
			}else {
				throw new FindException("상품이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
		
	}

	public static void main(String[] args) {
		ProductOracleRepository repository = new ProductOracleRepository();
//		int startRow = 2;
//		int endRow = 4;
//		try {
//			List<Product> list = repository.selectAll(startRow, endRow);
//			System.out.println(list);
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			System.out.println(repository.selectCount());
//		} catch (FindException e) {
//			e.printStackTrace();
//		}
		try {
			Product p = repository.selectByProdNo("XXX"); //"C0001");
			System.out.println(p);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}
	
	
}
