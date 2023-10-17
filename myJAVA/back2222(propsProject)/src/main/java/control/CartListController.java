package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

public class CartListController implements Controller {
	private static CartListController controller = new CartListController();
	private CartListController() {
		
	}
	public static CartListController getInstance() {
		return controller;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession();
		System.out.println("in cartlist: " + session.getId());
		Map<String,Integer> cart = (Map<String, Integer>) session.getAttribute("cart");
		if(cart == null) {
			Map <String, String> map = new HashMap<>();
			map.put("msg", "장바구니가 비었습니다");
			out.print(mapper.writeValueAsString(map));
		}else {
			List<Map<String,Object>> list = new ArrayList<>();
			ProductService service = ProductService.getInstance();
			for(String prodNo : cart.keySet()) {
				int quantity = cart.get(prodNo);
				Map<String, Object> map = new HashMap<>();
//				map.put("prodNo", prodNo);
//				map.put("quantity", quantity);
//				list.add(map);			
				try {
					Product p = service.findByProdNo(prodNo);
					map.put("product", p);
					map.put("quantity", quantity);
					list.add(map);
				} catch (FindException e) {
					e.printStackTrace();
				}
			}			
			out.print(mapper.writeValueAsString(list));
		}
		return null;
	}
}
