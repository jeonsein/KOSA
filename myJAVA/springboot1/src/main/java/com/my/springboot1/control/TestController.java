package com.my.springboot1.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@GetMapping("/a")
	public String a() {
		return "a.jsp"; // view name
	} // a()
	
	@GetMapping("/b")
	@ResponseBody
	public String b() {
		return "WELCOME_SENGNA"; // 응답내용
	} //b()
	
	@GetMapping("/c")
	@ResponseBody
	public Map<String, Object> c() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", 1);
		map.put("msg", "JSON 문자열");
		
		return map;
		
	} // c()
	
	/*
	// 자료형: String
	@GetMapping("/d")
	@ResponseBody
	// http://localhost:8881/boot1/d?id=aaa&sal=123
	// http://localhost:8881/boot1/d?sal=123
	public void d(String id, int sal) {
		System.out.println(id.length() + " : " + sal);
	} // d()
	*/
	
	// 자료형: Optional
	@GetMapping("/d")
	@ResponseBody
	public void d(@RequestParam(name = "id") Optional<String> optId, int sal) {
		/*
		if(optId.isPresent()) {	// if절이 true = id의 값이 존재하는 경우(= null이 아닌 경우)
			String id = optId.get();
			System.out.println(id.length() + " : " + sal);
		} // if
		*/
		// 위아래 동일 효과
		optId.ifPresent((id) -> {
			System.out.println(id.length() + " : " + sal);
		});
		
	} // d()
	
} // end class
