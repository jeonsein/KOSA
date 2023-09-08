package control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifecycleServlet() {
        super();
        System.out.println("LifecycleServlet 생성자 호출됨");
//		ServletContext sc = this.getServletContext();
//		System.out.println(sc.getRealPath("a.jpg"));
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("LifecycleServlet init() 호출됨");
		super.init(config);
		ServletContext sc = this.getServletContext();
		// 배포된 실제 경로의 a.jpg 경로 확인!
		System.out.println(sc.getRealPath("a.jpg"));
	}

	public void destroy() {
		System.out.println("LifecycleServlet destroy() 호출됨");
	}

//	protected void service(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		System.out.println("LifecycleServlet service() 호출됨");
//		String idValue = request.getParameter("id");
//		String pwdValue = request.getParameter("pwd");
//		System.out.println("요청 전달 데이터: id = " + idValue + ", pwd= "  + pwdValue);
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("LifecycleServlet doGet() 호출됨");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("LifecycleServlet doPost() 호출됨");
	}

} // end classS
