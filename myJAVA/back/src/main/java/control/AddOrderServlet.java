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
import com.my.exception.AddException;
import com.my.order.dto.OrderInfo;
import com.my.order.dto.OrderLine.OrderLine;
import com.my.order.service.OrderService;
import com.my.product.dto.Product;


@WebServlet("/addorder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	OrderService service;
	
	public AddOrderServlet() {
		service = new OrderService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 헤더 설정
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr;
		
		HttpSession session = request.getSession();
		String loginedId = (String)session.getAttribute("loginedId");
		
		Map<String, Object> map = new HashMap<>();
		
		if(loginedId == null) {		// 로그인 XX
			map.put("status", 0);
			map.put("msg", "로그인하세요.");
		} else {					// 로그인 OO
			Map<String, Integer> cart = (Map)session.getAttribute("cart");
			
			if(cart == null) {		// 장바구니 XX
				map.put("status", 0);
				map.put("msg", "장바구니가 비었습니다.");
			} else {				// 장바구니 OO
				OrderInfo info = new OrderInfo();
				info.setOrderId(loginedId);
				
				// Build Pattern
//				OrderInfo info = OrderInfo.builder()
//								.orderId(loginedId)
//								.build();
				
				List<OrderLine> lines = new ArrayList<>();
				info.setLines(lines);
				
				// 카트의 내용을 꺼내서 lines에 담기!
				for(String prodNo : cart.keySet()) {
					int quantity = cart.get(prodNo);
					
					OrderLine line = new OrderLine();
					Product p = new Product();
					p.setProdNo(prodNo);
					line.setOrderP(p);
					line.setOrderQuantity(quantity);
					lines.add(line);
				} // for
				
				try {						// 추가 성공
					service.add(info);
					session.removeAttribute("cart"); // 장바구니 비우기
					
					map.put("status", 1);
				} catch (AddException e) {	// 추가 실패
					e.printStackTrace();
					map.put("status", 0);
					map.put("msg", e.getMessage());
				} // try-catch
			
			} // inner-if-else
			
		} // outer-if-else
		
		// 프론트에게 응답
		out.print(mapper.writeValueAsString(map));
		
	} // doGet()

} // end class
