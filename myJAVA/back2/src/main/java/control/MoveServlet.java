package control;

import java.io.IOException;
import java.io.PrintWriter;

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
//		RequestDispatcher rd = req.getRequestDispatcher("http://naver.com"); // /back/req로 url 요청됨! = 서블릿 path를 적어줌!
//		rd.forward(req, res); // 해당 메소드를 요청하면 서버 사이드에 만들어진 req와 res 객체가 
							  // 이 메소드를 만나 다른 자원들에게 전달이 됨! (돌아오지는 X) 
//		rd.include(req, res); // 다른 자원의 Content를 현재 자원에 포함함! (다른 자원에게 보냈다가 다시 되돌아옴)
		
//		-------------
		
//		RequestDispatcher rd = req.getRequestDispatcher("req");
//		rd.forward(req, res);
		
		res.setContentType("text/html;charset=utf-8");
		// 출력 버퍼
		PrintWriter out = res.getWriter();
		
		// # FORWARD
//		out.print("BEFORE FORWARD");
//		RequestDispatcher rd = req.getRequestDispatcher("res");
//		rd.forward(req, res);
//		out.print("AFTER FORWARD");
		
		// # INCLUDE
		out.print("BEFORE INCLUDE");
		RequestDispatcher rd = req.getRequestDispatcher("res");
		rd.include(req, res);
		out.print("AFTER INCLUDE");
		
	} // doGet()

} // end class
