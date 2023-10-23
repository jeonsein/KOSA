package control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class TestRestController {
	@GetMapping("/n")
	//@ResponseBody
	public void n() {}
	
	@GetMapping("/o")
	//@ResponseBody
	public void o() {}
}
