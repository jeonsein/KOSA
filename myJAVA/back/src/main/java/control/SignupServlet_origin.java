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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.AddException;


@WebServlet("/signup")
public class SignupServlet_origin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CustomerService service;
	public SignupServlet_origin() {
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
		ObjectMapper mapper = new ObjectMapper();
		
		// 세션 생성
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			Customer c = new Customer(id, pwd, name, null);
			// 요청 전달 데이터로 초기화된 고객 객체
			service.signup(c);
			
			map.put("status", 1);
			map.put("msg", "가입성공");
			
		} catch(AddException e) {
			map.put("status", 0);
			map.put("msg", e.getMessage());
		} // try-catch
		
		// JSON 문자열 응답
		out.print(mapper.writeValueAsString(map));
		
	} // doPost()

} // end class
