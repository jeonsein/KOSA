public class JDBCTest {

	public static void main(String[] args) {

		// 1. JDBC 드라이버 설치
		
		// 2. 드라이버 클래스들 JVM에 로드 
		// (프로젝트 우클릭 후 BuildPath - add external 어쩌구 설정해서 jar 추가해주기)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC 드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			return; // void로 선언된 method는 return;으로 종료 가능
			
		} // try-catch
		
		// 3. DB와 연결
		
		
	} // end main
	
} // end class
