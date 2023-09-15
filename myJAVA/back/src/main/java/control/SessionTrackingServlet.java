package control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessiontracking")
public class SessionTrackingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		String sessionId = session.getId();
		boolean flag = session.isNew(); // 세션 생성 여부
		long time = session.getLastAccessedTime(); // 최종 사용 시간
		
		System.out.println("세션 ID: " + sessionId);
		System.out.println("세션 생성 여부: " + flag);
		System.out.println("세션 최종 사용 시간: " + new Date(time));
		
	} // doGet()

} // end class
