package control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;
import com.my.util.PageGroup;


@RestController
public class ProductController {

	// 스프링 빈으로 등록
	@Autowired
	private ProductService service;
	
	@GetMapping("/productlistjson")
	public PageGroup<Product> ProductList(@RequestParam(name = "currentPage",
												required = false,
												defaultValue = "1")
									int cp) {
		
		try {
			PageGroup<Product> pg = service.findAll(cp);
			
			return pg;			
		} catch(FindException e) {
			return null;
		} //  try-catch
		
	} // ProductList()
	
	@GetMapping("productjson")
	public Product productJson(String prodno) {
		
		try {
			Product p = service.findByProdNo(prodno);
			
			return p;
		} catch (FindException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("msg", e.getMessage());
		} // try-catch
		return null;
	}  // productJson

} // end class
