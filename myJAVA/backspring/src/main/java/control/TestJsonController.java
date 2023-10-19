package control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.product.dto.Product;

@Controller
public class TestJsonController {
	
	@GetMapping(value = "/h", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String h() {
		return "응답입니당^ㅇ^";
	} // h()
	
	// ------------------------------------
	
	@GetMapping(value = "/i", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String i() {
		String jsonStr = "{";
		
		jsonStr += "\"status\": 0";
		jsonStr += "}";
		
		return jsonStr;
	} // i()
	
	// ------------------------------------
	
	@GetMapping(value = "/j", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> j() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("status", 1);
		map.put("msg", "JSON");
		
		return map;
	} // j()
	
	// ------------------------------------
	
	@GetMapping(value = "/k")
	@ResponseBody
	public Product k() {
		
		Product p = new Product();
		
		p.setProdNo("C1216");
		p.setProdName("커햄");
		
		return p;
		
	} // k()
	
	// ------------------------------------
	
	// ResponseEntity = 응답 전체
	// 스프링용 mvc 모듈에서만 사용할 수 있음
	@GetMapping(value = "/m", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> m() {
		
		String body = "응답 내용임다!";
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ResponseEntity<String> entity = new ResponseEntity<String>(body, status);
		
		return entity;
		
	} // m()
	
} // end class