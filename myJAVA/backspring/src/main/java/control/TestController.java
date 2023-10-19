package control;

import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.product.dto.Product;

@Controller
public class TestController {

	// 메소드도 POJO이기 때문에 규격화되어 있지 않음!
	// 단, public이어야 함!
	@GetMapping("/a")
	public ModelAndView a(@RequestParam(name="n") String name, 
			@RequestParam(required = false, defaultValue = "0") int sal) {
		
		System.out.println("a() invoked.");
		System.out.println("name: " + name + ", sal: " + sal);
		
		return null;
		
	} // a()
	
	// ------------------------------
	
	@GetMapping("/b")
	// /b?cb=one&cb=two&cb=three 요청 전달 데이터값이 여러개인 경우!
	public ModelAndView b(String[] cb) {
		
		/*
		for(String e : cb) {
			System.out.println(e);
		} // for
		*/
		
		// 반복문 대신 스트림 사용하기!
		Stream<String> st = Stream.of(cb);
		System.out.println(st);
		
		return null;
	} // b()
	
	// ------------------------------	
	
	@GetMapping("/c")
	public String c(Model model) {
		model.addAttribute("msg", "셍나는스프링이조아");	// 모델에 메세지 추가
		
		String viewName = "c.jsp";
		
		// viewer의 역할을 할, jsp를 반환
		return viewName;
		
	} // c()
	
	// ------------------------------
	
	// ProductDTO p
	@GetMapping("/d")
	// /d?prodNo=C0001&prodName=바나나캣&prodPrice=1000
	public ModelAndView d(Product p) {
		System.out.println(p);
		
		return null;
	} // d()
	
	// ------------------------------
	
	@GetMapping("/e")
	public ModelAndView e() {
		ModelAndView mnv = new ModelAndView();
		
		// mnv.setViewName("e.jsp");		// View영역의 View 이름을 설정함
		mnv.setViewName("e");
		mnv.addObject("msg", "셍나는 스프링이 더 조아"); // Model영역의 Model 속성을 설정함
	
		return mnv;
		
	} // e()
	
	// ------------------------------
	
	@GetMapping("/f") 
	public String f() {
//		String viewName = "f.jsp";
		String viewName = "f";
		
		return viewName;
	} // f()
	
	// ------------------------------
	
	@GetMapping("g")
	public void g() {
		
	} // g()
	
} // end class
