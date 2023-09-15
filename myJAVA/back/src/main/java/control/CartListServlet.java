package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.service.ProductService;


@WebServlet("/cartlist")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 서블릿이 응답할 형식 지정하기
		response.setContentType("application/json;charset=utf-8");
		
		// 헤더 설정
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		
		// 세션 생성
		HttpSession session = request.getSession();
		
		// session 객체의 Attribute값 얻기 (이름: "cart")
		Map<String, Integer> cart = (Map)session.getAttribute("cart");	
		
		if(cart == null) {
			Map <String, String> map = new HashMap<>();
			map.put("msg", "장바구니가 비었습니다.");
			out.print(mapper.writeValueAsString(map));
		} else {
			List<Map<String, Object>> list = new ArrayList<>();
			
			for(String prodNo : cart.keySet()) {
				int quantity = cart.get(prodNo);
				Map<String, Object> map = new HashMap<>();
				map.put("prodNo", prodNo);
				map.put("quantity", quantity);
				list.add(map);
			} // for
			
			// JSON 문자열 응답
			out.print(mapper.writeValueAsString(list));
			
		} // if-else
		
	} // doGet()

} // end class