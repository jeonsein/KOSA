package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String envFileName = "control.properties";
	private Properties env;
    
	public DispatcherServlet() {
		super();
	} // constructor

	@Override
	public void init() throws ServletException {
		super.init();
		
		env = new Properties();
		
		ServletContext sc = this.getServletContext();
		String realPath = sc.getRealPath("WEB-INF\\classes\\com\\my\\env\\" + envFileName);
		System.out.println("in DispatcherServlet의 init(): realPath= " + realPath);
		
		try {
			env.load(new FileInputStream(realPath));
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		
	} // init()
	
//	---------------------------------

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 여기 헤더를 설정해놓으면 각 컨트롤러에서 굳이 따로 설정해줄 필요가 없음!!
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		System.out.println("request.getServletPath()=" + request.getServletPath());
		
		/*
		if(request.getServletPath().equals("/productjson")) {
			ProductJsonController control = new ProductJsonController();
			control.execute(request, response);
		} else if(request.getServletPath().equals("/productlistjson")) { 
			ProductListJsonController control = new ProductListJsonController();
			control.execute(request, response);
		} 
		*/
		
		/*
		String className = env.getProperty(request.getServletPath());
		try {
			// 클래스 이름에 해당하는 .class 파일을 찾아서 JVM으로 로드
			Class clazz = Class.forName(className);
//			clazz.newInstance(); // 지금 로드된 클래스 타입의 객체 생성 작업
			
			// Class들도 싱글톤 패턴으로 변경했다면,
//			Method m = clazz.getMethod("getInstance");
			
			// clazz.newInstance();는 deprecated 되었기 때문에 아래 코드로 변경!
			Controller controller = (Controller)clazz.getDeclaredConstructor().newInstance();
			controller.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch
		*/
		
		
		// 컨트롤러를 싱글톤 패턴으로 변경한 이후!
		System.out.println("request.getServletPath()=" + request.getServletPath());
		
		String className = env.getProperty(request.getServletPath());
		
		try {
			Class<?> clazz = Class.forName(className);//클래스이름에 해당하는 .class파일 찾아서 JVM으로 로드
			
			Controller controller;
			
			try {
				Method method = clazz.getMethod("getInstance");
				controller = (Controller)method.invoke(null);//static인 getInstance()메서드호출
			} catch (NoSuchMethodException e) {			
				controller = (Controller)clazz.getDeclaredConstructor().newInstance();
			}
			
			String path = controller.execute(request, response);
			
			if(path!=null) {
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);
			}
			
			// controller.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // service()

} // end class
