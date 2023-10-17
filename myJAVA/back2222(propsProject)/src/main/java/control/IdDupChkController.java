package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;


public class IdDupChkController extends CustomerController {
	private static final long serialVersionUID = 1L;
	private static IdDupChkController controller = new IdDupChkController();
	private IdDupChkController() {}
	public static IdDupChkController getInstance() {
		return controller;
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		
		String id = request.getParameter("id");		
		try {
			service.idDupChk(id);
			map.put("status", 0);
		} catch (FindException e) {
			map.put("status", 1);
		}
		out.print(mapper.writeValueAsString(map));
		return null;
	}

}
