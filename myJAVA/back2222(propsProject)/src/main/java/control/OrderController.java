package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.order.service.OrderService;

public abstract class OrderController implements Controller {
	protected OrderService service;
	public OrderController() {
		service = OrderService.getInstance();
	}
}
