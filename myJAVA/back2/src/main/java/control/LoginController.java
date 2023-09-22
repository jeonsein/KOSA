package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;


public class LoginController extends CustomerController {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		response.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = response.getWriter();
//		out.print("로그인 성공!");
		
		// JSON 문자열 반환을 위함
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginedId"); // 초기화
		
		// id랑 pwd값 얻어오기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id+" : "+pwd);
		
		try {						// 로그인 성공!
			
			service.login(id, pwd);
			
			map.put("status", 1);
			map.put("msg", "로그인 성공");
			
			session.setAttribute("loginedId", id);
			
		} catch (FindException e) { // 로그인 실패!
			e.printStackTrace();
			
			map.put("status", 0);
			map.put("msg", "로그인 실패");
			
		} // try-catch
		
		// 응답
		String jsonStr = mapper.writeValueAsString(map);
		out.print(jsonStr);
		
		return null;
	} // execute()

} // end class
