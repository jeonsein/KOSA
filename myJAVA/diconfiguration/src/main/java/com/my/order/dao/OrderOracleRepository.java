package com.my.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.product.dto.Product;
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
		}
		try {
			insertInfo(conn, info.getOrderId());
			insertLine(conn, info.getLines());
		}finally {
			MyConnection.close(conn, null, null);
		}
	}
	
	public void insertInfo(Connection conn, String id) throws AddException{
		PreparedStatement pstmt = null;
		String insertInfoSQL = "INSERT INTO order_info(order_no, order_id, order_dt)"
				+ "VALUES (order_seq.NEXTVAL,  ?,  SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(insertInfoSQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			MyConnection.close(null, pstmt, null);
		}
	}
	
	public void insertLine(Connection conn, List<OrderLine> lines) throws AddException{
		PreparedStatement pstmt = null;
		String insertLineSQL = 
				"INSERT INTO order_line(order_line_no, order_prod_no, order_quantity)"
				+ " VALUES (order_seq.CURRVAL, ?,  ?)";
		try {
			pstmt = conn.prepareStatement(insertLineSQL);
			for(OrderLine line: lines) {
				String prodNo = line.getOrderP().getProdNo();
				int quantity = line.getOrderQuantity();
				pstmt.setString(1, prodNo);
				pstmt.setInt(2, quantity);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(null, pstmt, null);
		}
	}
	public List<OrderInfo> selectById(String orderId) throws FindException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyConnection.getConnection();
			String selectByIdSQL = "SELECT order_no, order_id, order_dt,\r\n"
					+ "          order_quantity,\r\n"
					+ "          p.prod_no, prod_name,prod_price\r\n"
					+ "FROM order_info info JOIN order_line line ON ( info.order_no = line.order_line_no)\r\n"
					+ "                              JOIN product p ON (line.order_prod_no = p.prod_no)\r\n"
					+ "WHERE order_id = ?\r\n"
					+ "ORDER BY order_dt DESC";
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, orderId);
			
			rs = pstmt.executeQuery();			
			
			List<OrderInfo> list = new ArrayList<>();
			int oldOrderNo = 0; 
			List<OrderLine> lines = null;
			while(rs.next()) {
				int orderNo = rs.getInt("order_no");
				if(oldOrderNo != orderNo) {	//첫행, 주문번호가 다르면
					OrderInfo info = new OrderInfo();
					info.setOrderNo(orderNo);
					info.setOrderId(orderId);
					info.setOrderDt(rs.getDate("order_dt"));
					
					lines = new ArrayList<>();
					info.setLines(lines);
					list.add(info);
					
					oldOrderNo = orderNo;
				}
				OrderLine line = new OrderLine();
				line.setOrderLineNo(orderNo);
				
				Product p = new Product();
				line.setOrderP(p);
				p.setProdNo(rs.getString("prod_no"));
				p.setProdName(rs.getString("prod_name"));
				p.setProdPrice(rs.getInt("prod_price"));				
				line.setOrderQuantity(rs.getInt("order_quantity"));
				
				lines.add(line);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		}
	}

}
