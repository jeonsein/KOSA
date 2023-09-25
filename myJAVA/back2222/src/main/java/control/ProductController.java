package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.product.service.ProductService;

public abstract class ProductController implements Controller {
	protected ProductService service;
	public ProductController() {
		System.out.println("ProductController생성자호출됨");
		service = ProductService.getInstance();
	}
}
