package control;

import com.my.product.service.ProductService;

public abstract class ProductController implements Controller {

	protected ProductService service;
	public ProductController() {
		// 서비스를 싱글톤 패턴으로 변경하였기 때문에, getInstance써야댐
		service = ProductService.getInstance(); 
	} // constructor
	
} // end class
