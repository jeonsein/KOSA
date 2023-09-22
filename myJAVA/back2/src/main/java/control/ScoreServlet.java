package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int totalScore = 0;
	private int totalCnt = 0;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
	
		// 요청 전달 데이터 얻기 -----------------------------------------
		String star = req.getParameter("star");
		
		// 계산 ----------------------------------------------------------
		totalScore += Integer.parseInt(star);
		totalCnt++;
		
		float avgScore = (float)totalScore/totalCnt;
		
		// 요청 속성 설정 -------------------------------------------------
		req.setAttribute("ts", totalScore);	// 총점
		req.setAttribute("tc", totalCnt);	// 총 참여자수
		req.setAttribute("as", avgScore);	// 평점
		
		// JSP 페이지(VIEWER)로 이동 --------------------------------------
		RequestDispatcher rd = req.getRequestDispatcher("scoreresult.jsp");
		rd.forward(req, res);
		
	} // doGet()

} // end class
