package com.my.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.my.exception.AddException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine.OrderLine;
import com.my.sql.MyConnection;

public class OrderOracleRepository implements OrderRepository {

	@Override
	public void insert(OrderInfo info) throws AddException {
		Connection conn = null;
		
		try {
			conn = MyConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} // try-catch
		
		try {
			insertInfo(conn, info.getOrderId());
			insertLine(conn, info.getLines());
		} finally {
			MyConnection.close(conn, null, null);
		} // try-finally
	
	} // insert()
	
//	----------------------------------------------------------------
	
	public void insertInfo(Connection conn, String id) throws AddException {
		
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO order_info(order_no, order_id, order_dt) "
							 + "VALUES (order_seq.NEXTVAL, ?, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(insertInfoSQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null); // Connection은 close 안함!
		} // try-catch-finally
		
	} // insertInfo()
	
//	----------------------------------------------------------------
	
	public void insertLine(Connection conn, List<OrderLine> lines) throws AddException {
	
		PreparedStatement pstmt = null;
		String insertLineSQL = "INSERT INTO order_line(order_line_no, order_prod_no, order_quantity) "
							 + "VALUES (order_seq.CURRVAL, ?,  ?)";
		
		try {
			pstmt = conn.prepareStatement(insertLineSQL);
			
			for(OrderLine line : lines) {
				String prodNo = line.getOrderP().getProdNo();
				int quantity = line.getOrderQuantity();
				
				pstmt.setString(1, prodNo);
				pstmt.setInt(2, quantity);
				pstmt.executeUpdate();
			} // for
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null); // Connection은 close 안함!
			// Connection은 insert()에서 닫아줄거임!
		} // try-catch-finally
		
	} // insertLine()
	
//	----------------------------------------------------------------
	
} // end class
