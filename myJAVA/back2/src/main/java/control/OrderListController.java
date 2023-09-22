package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;

public class OrderListController extends OrderController {

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

		HttpSession session = request.getSession();
		System.out.println("in orderlist: " + session.getId());

		String loginedId = (String)session.getAttribute("loginedId");

		Map<String, Object> map = new HashMap<>();

		if(loginedId == null) {			// 로그인 XX
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		} else {						// 로그인 OO

			try {
				List<OrderInfo> list = service.findById(loginedId);

				map.put("status", 1);
				map.put("list", list);				
			} catch (FindException e) {
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
			} // try-catch	

		} // if-else

		// JSON 응답
		out.print(mapper.writeValueAsString(map));

		return null;
	} // execute()

} // end class
