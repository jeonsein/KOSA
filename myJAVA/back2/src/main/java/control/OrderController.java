package control;

import com.my.order.service.OrderService;

public abstract class OrderController implements Controller {

	protected OrderService service;
	public OrderController() {
		// 서비스를 싱글톤 패턴으로 변경하였기 때문에, getInstance써야댐
		service = OrderService.getInstance(); 
	} // constructor
	
} // end class
