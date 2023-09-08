package control;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/req")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tValue = request.getParameter("t"); 
		// t 요청 전달데이터가 없으면 null, 
		// t 요청 전달데이터는 있지만 value가 없다면 비어있는 문자열 
		
//		String cValue = request.getParameter("c"); // key는 같지만 value가 다른 값 여러개가 있다면 첫번째만 보여줌
		String []cValues = request.getParameterValues("c");
		if(cValues != null) {
			for(int i = 0; i < cValues.length; i++) {
				System.out.println(cValues[i]);
			} // for
		} // if
		
//		System.out.println(tValue + " : " + cValue);
		
		System.out.println("request.getContextPath(): " + request.getContextPath());
		System.out.println("request.getRequestURL(): " + request.getRequestURL());
		System.out.println("request.getRequestURI(): " + request.getRequestURI());
		System.out.println("request.getServletPath(): " + request.getServletPath());
		
//		-----------------------
		Enumeration<String> em = request.getHeaderNames();
		while(em.hasMoreElements()) {
			String name = em.nextElement();
			String value = request.getHeader(name);
			
			System.out.println(name + " : " + value);
		} // while
		
		// Attribute는 어떤 값도 다 저장할 수 있음 
		// -> Object 타입으로 Upcasting해서 다 저장해야 하기 때문!
		request.setAttribute("msg", "요청속성1");
		request.setAttribute("cnt", 1216); // AutoBoxing : (int -> Integer) -> Object = Upcasting
										   // AutoBoxing : (int) -> Object는 될 수 없기 때문에 Integer의 오토박싱이 필요함!
		 // Integer의 오토박싱이 필요함!
		request.setAttribute("now", new Date());
		
		// Object 타입으로 가져오기 때문에 원래 타입으로 형변환해주기!
		String msg = (String)request.getAttribute("msg");
		int cnt = (Integer)request.getAttribute("cnt");  // (int) 말고 저장된 자료형의 최종형이 
														 // Object이기 때문에 Integer로!
		
		request.removeAttribute("now"); // 제거
		
	} // RequestServlet()

} // end class
