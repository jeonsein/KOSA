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
import com.my.product.dto.Product;
import com.my.product.service.ProductService;


@WebServlet("/cartlist")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService service;
	
	public CartListServlet() {
		service = new ProductService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
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
		
		// session 객체의 Attribute값 얻기 (이름: "cart")
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");	
		
		List list = new ArrayList<>();
		
		if(cart == null) {
			cart = new HashMap<String, Integer>();
			session.setAttribute("cart", cart);
		} // if
		
		Product p;
		for(String prodNo : cart.keySet()) {
			int quantity = cart.get(prodNo);
			
			try {
				p = service.findByProdNo(prodNo);
				Map map = new HashMap<>();
				
				map.put("product", p);
				map.put("quantity", quantity);
				
				list.add(map);
			} catch (FindException e) {
				e.printStackTrace();
			} // try-catch
			
		} // for
			
		// JSON 문자열 응답
		
		String jsonStr = mapper.writeValueAsString(list);
		System.out.println(jsonStr);
		out.print(jsonStr);
		
	} // doGet()


} // end class