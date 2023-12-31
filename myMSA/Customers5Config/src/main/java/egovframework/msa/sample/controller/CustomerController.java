package egovframework.msa.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@GetMapping("/{customerId}")
	public String getCustomerDetail(@PathVariable String customerId) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("#8082 : request customerId :" + customerId);
		return "[#8082 Customer id = " + customerId + " at " + System.currentTimeMillis() + "]";
	}
}