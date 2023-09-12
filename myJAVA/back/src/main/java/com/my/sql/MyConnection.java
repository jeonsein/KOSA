package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {

	/**
	 * JDBC 드라이버 로드 및 DB와의 연결작업을 수행함.
	 * @return Connection 객체를 반환!
	 * @throws Exception 드라이버 클래스를 찾지 못하거나, DB와의 연결 실패 시 예외 발생함.
	 */
	public static Connection getConnection() throws Exception {
		// JDBC 드라이버 로드
		Class.forName("oracle.jdbc.OracleDriver");
		
		// DB연결
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user ="hr";
		String password = "hr";
		return DriverManager.getConnection(url, user, password);
		
	} // getConnection()
	
//	-----------------------------------------------------------------
	
	/**
	 * DB와의 연결을 닫음.
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		// Statement 사용 이유: 하위 요소들까지 사용 가능하도록!
		
		// DB연결해제
		if(rs != null) {
			try { rs.close(); } catch (SQLException e) {} // try-catch			
		} // if
		
		if(stmt != null) {
			try { stmt.close(); } catch (SQLException e) {}	// try-catch
		} // if
		
		if(conn != null) {
			try { conn.close(); } catch (SQLException e) {}	// try-catch
		} // if
		
	} // close()
	
} // end class
