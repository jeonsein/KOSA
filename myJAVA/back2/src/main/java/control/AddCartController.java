package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddCartController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서블릿이 응답할 형식 지정하기
		response.setContentType("application/json;charset=utf-8");
		// 헤더 설정
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		// 1. 요청 전달 데이터 얻기
		String prodNo = request.getParameter("prodNo");
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		System.out.println("요청 전달 데이터: " + prodNo + ":" + quantity);

		// 2. HttpSession 객체 얻기
		HttpSession session = request.getSession();
		String sessionId = session.getId();

		// 3. session 객체의 Attribute값 얻기 (이름: "cart")
		Map<String, Integer> cart = (Map)session.getAttribute("cart");

		// 4. cart Attribute가 없으면, 객체 생성 후 cart Attribute로 추가
		if(cart == null) {
			cart = new HashMap<String, Integer>();
			session.setAttribute("cart", cart);
		} // if

		// 5. 요청 전달 데이터 상품 번호가 key, 수량이 value로 
		// cart Attribute의 요소로 추가
		//				cart.put(prodNo, quantity);
		//				session.setAttribute("cart", cart);

		// 5-1. 요청 전달 데이터 누적 추가
		// prodNo로 value값(수량) 확인해서 변수에 넣고 요청 전달 데이터 값 더해주기
		if(cart.containsKey(prodNo)){ // 맵에서 인자로 보낸 값이 있으면 true
			int newQuantity = cart.get(prodNo);
			cart.put(prodNo, quantity + newQuantity); // value 누적
		} else {
			cart.put(prodNo, quantity);
		} // if-else

		// 6. cart Attribute 요소들을(카트의 내역) 모두 출력한다.
		System.out.println(cart);
		
		return null;
	} // execute()

} // end class
