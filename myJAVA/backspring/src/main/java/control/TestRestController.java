package control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class TestRestController {

	@GetMapping("/n")
//	@ResponseBody
	public void n() {} // n()
	
	@GetMapping("/o")
//	@ResponseBody
	public void o() {} // n()
	
} // end class
