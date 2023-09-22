package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;

public class ProductListJsonController extends ProductController {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 서블릿이 응답할 형식 지정하기
		response.setContentType("application/json;charset=utf-8");

		// 헤더 설정
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		// 응답 출력 스트림 얻기
		PrintWriter out = response.getWriter();

		// 요청 전달 데이터 currentPage 얻기
		String currentPage = request.getParameter("currentPage");
		int cp = 1;

		if(currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		} // if

		// Jackson용 객체
		ObjectMapper mapper = new ObjectMapper();

		try {
			PageGroup<Product> pg = service.findAll(cp);
			String jsonStr = mapper.writeValueAsString(pg);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
		} // try-catch

		return null;
	} // execute()

} // end class
