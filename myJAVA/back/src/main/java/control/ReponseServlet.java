package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/res")
public class ReponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		// 응답 형식 설정
		res.setContentType("text/html;charset=utf-8");
		
		// 응답용 출력 스트림을 획득!
		PrintWriter out = res.getWriter(); // 문자 단위 출력
		// ServletOutputStream sos = res.getOutputStream() // 바이트 단위 출력
		
		out.print("WELCOME 셍나's 서블릿입니당 :-) ");
		
	} // doGet()

} // end class
