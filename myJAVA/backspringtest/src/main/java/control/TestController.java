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
	//public ModelAndView m(HttpServletRequest request, 
//	                        HttpServletResponse response,
//	                        HttpSession session)
	@GetMapping("/a")
	public ModelAndView a(@RequestParam(name = "n")   String name, //요청전달데이터명:n ex) /a?n=oh
			              @RequestParam(required = false, defaultValue = "0")  int sal) {
		System.out.println("a()호출됨 name:" + name + ",sal:" + sal);
		return null;
	}
	
	@GetMapping("/b")
	// /b?cb=one&cb=two&cb=three 요청전달데이터값이 여러개인 경우
	public ModelAndView b(String[] cb) {
//		for(String e: cb) {
//			System.out.println(e);
//		}
		Stream<String>st = Stream.of(cb);
		System.out.println(st);
		return null;
	}
	
	@GetMapping("/c")
	public String c(Model model) {
		model.addAttribute("msg", "안녕하세요");
		String viewName = "c.jsp";
		return viewName;
	}
	
	@GetMapping("/d")
	// /d?prodNo=C0001&prodName=아메리카노&prodPrice=1000
	public ModelAndView d(Product p) { //Command객체 : 요청전달데이터를 property로 갖는 객체
		System.out.println(p);
		return null;
	}
	
	//반환형
	@GetMapping("/e")
	public ModelAndView e() {
		ModelAndView mnv = new ModelAndView();
		//mnv.setViewName("e.jsp"); //View의 view이름설정
		mnv.setViewName("e");
		mnv.addObject("msg", "WELCOME"); //Model의 속성설정
		return mnv;
	}
	
	@GetMapping("/f")
	public String f() {
//		String viewName = "f.jsp";
		String viewName = "f";
		return viewName;
	}
	
	@GetMapping("/g")
	public void g() {		
	}
	
	
	
}






