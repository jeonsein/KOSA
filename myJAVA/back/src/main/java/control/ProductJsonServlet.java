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
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;


@WebServlet("/productjson")
public class ProductJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductService service;
	
	public ProductJsonServlet() {
		service = new ProductService();
	} // constructor
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 응답 형식을 json 형태로!
		response.setContentType("application/json;charset=utf-8");
		
		// CORS 문제 해결
		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper(); // Jackson!!
		
		// 요청 전달 데이터 얻기
		String prodNo = request.getParameter("prodno");
		
		try {
			Product p = service.findByProdNo(prodNo);
			
			// 상품의 정보들을 json 문자열로 변환하여 응답해야 함
			String jsonStr = mapper.writeValueAsString(p);
			out.print(jsonStr);
			
		} catch (FindException e) {
			e.printStackTrace();
			
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
			String jsonStr = mapper.writeValueAsString(map);
			out.print(jsonStr);
			
		} // try-catch
		
	} // doGet()

} // end class
