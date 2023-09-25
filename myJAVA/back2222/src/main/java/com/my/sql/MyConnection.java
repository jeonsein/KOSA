package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	/**
	 * JDBC드라이버 로드및 DB와 연결한다
	 * @return Connection객체를 반환한다
	 * @throws Exception 드라이버클래스를 찾지 못하거나 DB연결실패시 예외발생한다
	 */
	public static Connection getConnection() throws Exception{
		//JDBC드라이버로드
		Class.forName("oracle.jdbc.OracleDriver");
		//DB연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user ="hr";
		String password = "hr";
		return DriverManager.getConnection(url, user, password);	
		
//		String resource = "com/my/sql/mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory =
//		  new SqlSessionFactoryBuilder().build(inputStream);
//		
//		SqlSession session = sqlSessionFactory.openSession();
//		return session.getConnection();
		
	}
	/**
	 * DB와 연결을 닫는다
	 * @param conn 
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}			
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}			
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
