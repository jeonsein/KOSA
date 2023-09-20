package com.my.order.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine;
import com.my.product.dto.Product;
import com.my.sql.MyConnection;

public class OrderOracleMybatisRepository implements OrderRepository {

	private SqlSessionFactory sqlSessionFactory;
	
	public OrderOracleMybatisRepository() {
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
	public void insert(OrderInfo info) throws AddException {
		
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			
			insertInfo(session, info.getOrderId());
			insertLine(session, info.getLines());
			
			session.commit();
		} catch(Exception e) {
			session.rollback();
			
			throw new AddException(e.getMessage());
		} finally {
			
			if(session != null) {
				session.close();
			} // if
			
		} // try-finally
		
		/* MyBatis 변경 전
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
		*/
	
	} // insert()
	
//	----------------------------------------------------------------
	
	// public void insertInfo(Connection conn, String id) throws AddException { /* MyBatis 변경 전*/
	public void insertInfo(SqlSession session, String id) throws AddException {
		
		try {
			session.insert("com.my.order.OrderMapper.insertInfo", id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} // try-catch
		
		/* MyBatis 변경 전
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
		*/
		
	} // insertInfo()
	
//	----------------------------------------------------------------
	
//	public void insertLine(Connection conn, List<OrderLine> lines) throws AddException { /* MyBatis 변경 전*/
	public void insertLine(SqlSession session, List<OrderLine> lines) throws AddException {
	
		try {
			
			for(OrderLine line : lines) {
				session.insert("com.my.order.OrderMapper.insertLine", line);
			} // for
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} // try-catch
		
		/* MyBatis 변경 전
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
		*/
		
	} // insertLine()
	
//	----------------------------------------------------------------
	
	public List<OrderInfo> selectById(String orderId) throws FindException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyConnection.getConnection();
			
			String selectByIdSQL = "SELECT order_no, order_id, order_dt, "
					+ "          	order_quantity, "
					+ "          	p.prod_no, prod_name, prod_price "
					+ "FROM order_info info JOIN order_line line ON (info.order_no = line.order_line_no) "
					+ "                     JOIN product p ON (line.order_prod_no = p.prod_no) "
					+ "WHERE order_id = ? "
					+ "ORDER BY order_dt DESC";
			
			pstmt = conn.prepareStatement(selectByIdSQL);
			pstmt.setString(1, orderId);
			rs = pstmt.executeQuery();
			
			List<OrderInfo> list = new ArrayList<>(); // 주문 기본 정보들이 담길 리스트
			List<OrderLine> lines = null;
			
			int oldOrderNo = 0; 
			// 주문 번호 sequence로 구성 -> 주문 번호가 1부터 시작하기 때문에, 
			// DB에 저장되어 있는 주문 번호와 다른 번호로 세팅!
			
			while(rs.next()) {
				int orderNo = rs.getInt("order_no"); // 각 행의 주문 번호를 얻어옴
				
				// if 조건을 만족하려면 "첫 행"이거나, 주문 번호가 바뀔 때 만족함!
				if(oldOrderNo != orderNo) { // 첫 행일 경우, 두 변수의 값은 절대 같을 수 없음 (oldOrderNo가 0이기 때문)
					
					// 주문 번호가 바뀔 때 마다 OrderInfo 객체 새로 생성 + 셋팅
					OrderInfo info = new OrderInfo();
					info.setOrderNo(orderNo);
					info.setOrderId(orderId);
					info.setOrderDt(rs.getDate("order_dt"));
					
					lines = new ArrayList<>(); // List<OrderLine> lines = null;
					info.setLines(lines); // info에 lines가 세팅이 됨! (정보 등록은 아직 안된 상태)
					list.add(info); // order info들인 list에 info 추가!
					
					oldOrderNo = orderNo; // oleOrderNo = 0 -> 첫 행을 만나면 첫 행의 주문 번호로 변경됨!
				} // if
				
				// line의 주문 번호
				OrderLine line = new OrderLine();
				line.setOrderLineNo(orderNo);
				
				// line의 상품 객체들 세팅
				Product p = new Product();
				line.setOrderP(p); // 상품 객체들 추가
				p.setProdNo(rs.getString("prod_no"));
				p.setProdName(rs.getString("prod_name"));
				p.setProdPrice(rs.getInt("prod_price"));
				
				// line의 수량
				line.setOrderQuantity(rs.getInt("order_quantity"));
				
				// lines 추가!
				lines.add(line);
				
			} // while
			
			return list; // 서비스에서 받아가기!
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(conn, pstmt, rs);
		} // try-catch-finally
		
	} // selectById()
	
} // end class
