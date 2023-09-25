package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.customer.service.CustomerService;

public abstract class CustomerController implements Controller {
	protected CustomerService service;
	public CustomerController() {
		service = CustomerService.getInstance();
	}
}
