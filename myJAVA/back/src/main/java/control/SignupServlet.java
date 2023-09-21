package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.AddException;
import com.my.util.Attach;


@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CustomerService service;
	
	public SignupServlet() {
		service = new CustomerService();
	} // constructor
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 서블릿이 응답할 형식 지정하기
		response.setContentType("application/json;charset=utf-8");
		// 헤더 설정
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();  // JSON 문자열 만드는 API
		Map<String, Object> map = new HashMap<>(); // 응답 내용 
		
//		-------------------------------------------
		
		try {
			
			Attach attach = new Attach(request);
			
			String id = attach.getParameter("id");
			String pwd = attach.getParameter("pwd");
			String name = attach.getParameter("name");
			
			Customer c = new Customer(id, pwd, name, null);
			// 요청 전달 데이터로 초기화된 고객 객체
			service.signup(c);
			
			// 각각 try-catch로 잡아줘야지
			// _profile_ 파일이 없는 경우에도 _intro_ 파일을 생성할 수 있음
			// 가입된 아이디로 파일 첨부
			try {
				String originProfileFileName = attach.getFile("f1").get(0).getName();			
				attach.upload("f1", id + "_profile_" + originProfileFileName);
			} catch(Exception e) {
				
			} // try-catch
			
			try {
				String originIntroFileName = attach.getFile("f2").get(0).getName();			
				attach.upload("f2", id + "_intro_" + originIntroFileName);			
			} catch(Exception e) {
				
			} // try-catch
			
			map.put("status", 1);
			map.put("msg", "가입성공");
			
		} catch(Exception e) {
			map.put("status", 0);
			map.put("msg", e.getMessage());
		} // try-catch
		
		// JSON 문자열 응답
		out.print(mapper.writeValueAsString(map));
		
	} // doPost()

} // end class
