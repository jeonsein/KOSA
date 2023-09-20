package com.my.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.my.customer.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.sql.MyConnection;

public class CustomerOracleRepository implements CustomerRepository {

	@Override
	public Customer selectById(String id) throws FindException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// DB와 연결
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} // try-catch
		
		String selectByIdSQL = "SELECT * "
				+ " FROM customer "
				+ "	WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(selectByIdSQL); // SQL 구문을 DB로 송신
			pstmt.setString(1, id); // 바인드 변수 값을 설정
			rs = pstmt.executeQuery(); // 바인드 변수값을 DB로 송신
			
			// 행 1개라서 while 필요 X
			if(rs.next()) {
				return new Customer (id, rs.getString("PWD"), 
									 rs.getString("NAME"), null); 
									// 일단 address값 null로!
			} else {
				throw new FindException("해당하는 고객이 없습니다.");
			} // if-else
			
		} catch (SQLException e) {
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		} // // try-catch-finally
		
	} // selectById()

	@Override
	public void insert(Customer c) throws AddException {;;}
	
} // end class
