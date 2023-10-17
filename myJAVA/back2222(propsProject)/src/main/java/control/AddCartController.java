package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AddCartController implements Controller {
	private static final long serialVersionUID = 1L;
	static private AddCartController controller = new AddCartController();
	private AddCartController() {}
	public static AddCartController getInstance() {
		return controller;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodNo = request.getParameter("prodNo");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		
		HttpSession session = request.getSession();
		System.out.println("in addcart: " + session.getId());
		Map<String, Integer> cart = (Map)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		
		Integer oldQuantity = cart.get(prodNo);
		if(oldQuantity != null) {
			quantity += oldQuantity;
		}
		cart.put(prodNo, quantity);
		System.out.println("JSESSIONID:" + session.getId());
		System.out.println(cart);	
		return null;
	}
}