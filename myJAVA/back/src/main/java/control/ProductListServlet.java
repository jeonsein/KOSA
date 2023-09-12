package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.FindException;
import com.my.product.dto.PageGroup;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService service;
    
    public ProductListServlet() {
    	service = new ProductService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String currentPage = request.getParameter("currentPage");
		int cp = 1;
		
		if(currentPage != null && !currentPage.equals("")) {
			cp = Integer.parseInt(currentPage);
		} // if
		
		String path = "productlistresult.jsp"; // 정상 처리됐을 경우 이동할 경로
		try {
//			List<Product> list = service.findAll(cp);
//			request.setAttribute("list", list);
			PageGroup<Product> pb = service.findAll(cp);
		} catch (FindException e) {
			path = "fail.jsp"; // 실패의 경우, 이동할 경로
			request.setAttribute("msg", e.getMessage());
		} // try-catch
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	} // doGet()

} // end class
