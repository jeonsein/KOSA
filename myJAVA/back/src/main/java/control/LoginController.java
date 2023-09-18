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
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService service;
	
	public LoginController() {
		service = new CustomerService();
	} // constructor

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		res.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		
		res.setContentType("application/json;charset=utf-8");
		
		PrintWriter out = res.getWriter();
//		out.print("로그인 성공!");
		
		// JSON 문자열 반환을 위함
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		HttpSession session = req.getSession();
		session.removeAttribute("loginedId"); // 초기화
		
		
		// id랑 pwd값 얻어오기
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
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
		
	}	// doPost(req,res)

} // end class
