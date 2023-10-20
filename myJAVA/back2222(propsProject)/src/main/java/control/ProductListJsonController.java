package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.util.PageGroup;

public class ProductListJsonController extends ProductController {
	static private ProductListJsonController controller = new ProductListJsonController();
	public ProductListJsonController() {}
	public static ProductListJsonController getInstance() {
		return controller;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//응답형식
		response.setContentType("application/json;charset=utf-8");
		
		//응답출력스트림얻기
		PrintWriter out = response.getWriter();
		
		//요청전달데이터 currentPage얻기
		String currentPage = request.getParameter("currentPage");
		int cp = 1;
		if(currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			PageGroup<Product> pg = service.findAll(cp);
			//out.print("{ \"나이\":10 , \"주소\":\"서울특별시 양천구 목동\" }");
			String jsonStr = mapper.writeValueAsString(pg);
			out.print(jsonStr);
		} catch (FindException e) {
			e.printStackTrace();
		}
		//return "productlistresult.jsp";
		return null;
	}

}
