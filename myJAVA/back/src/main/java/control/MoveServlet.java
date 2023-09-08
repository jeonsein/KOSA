package control;

import java.io.IOException;

import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/move")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		// 페이지 이동 방법 #1
//		res.sendRedirect("http://naver.com");
		// 응답헤더 설정 후 응답됨!
		// 주소 URL을 바꿔버림 -> 클라이언트 측의 주소 재요청과 같음
		
		// 페이지 이동 방법 #2
		RequestDispatcher rd = req.getRequestDispatcher("http://naver.com"); // /back/req로 url 요청됨! = 서블릿 path를 적어줌!
		rd.forward(req, res);
		
	}

} // end class
