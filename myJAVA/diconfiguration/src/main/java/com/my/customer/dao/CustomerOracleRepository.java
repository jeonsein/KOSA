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
		try {
			conn = MyConnection.getConnection();			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectByIdSQL = "SELECT * "
				+ "FROM customer "
				+ "WHERE id=?";
		try {
			pstmt = conn.prepareStatement(selectByIdSQL);	//SQL구문을 DB로 송신	
			pstmt.setString(1, id); //바인드변수값 설정
			rs = pstmt.executeQuery(); //바인드변수값을 DB로 송신	
			if(rs.next()) {
				return new Customer(
						id,
						rs.getString("PWD"),
						rs.getString("NAME"),
						null
						);
			}else {
				throw new FindException("고객이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}		
	}

	@Override
	public void insert(Customer c) throws AddException {
		// TODO Auto-generated method stub
		
	}

}
