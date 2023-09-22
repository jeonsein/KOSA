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
		
		// 응답 형식 설정 : MIME "applicatino/json", "text/plain"
		res.setContentType("text/html;charset=utf-8");
//		res.setContentType("text/plain;charset=utf-8"); // 순수 text로만 해석함!
		
		// 응답용 출력 스트림을 획득!
		PrintWriter out = res.getWriter(); // 문자 단위 출력
		// ServletOutputStream sos = res.getOutputStream() // 바이트 단위 출력
		
		// 서블릿에서 직접 응답하기
		out.print("<h1>WELCOME</h1> <h3>셍나's 서블릿입니당 &#128057;</h3> ");
		out.print("<table>");
		out.print("<tr>");
		for(int i = 1; i <= 5; i++) {
			out.print("<td>"); out.print(i); out.print("</td>");
		} // for
		out.print("</tr>");
		out.print("</table>");
		
	} // doGet()

} // end class
