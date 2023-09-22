package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;

public class IdDupChkController extends CustomerController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
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
		
		Map<String, Object> map = new HashMap<>();

		try {
			service.idDupChk(id);
			// 고객이 있는 경우
			map.put("status", 0);
		} catch (FindException e) {
			// 고객이 없는 경우
			map.put("status", 1);
		} // try-catch
		
		// JSON 응답
		out.print( mapper.writeValueAsString(map));
		
		return null;
		
	} // execute()

} // end class
