package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.order.dto.OrderInfo;

public class OrderListController extends OrderController {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ObjectMapper mapper = new ObjectMapper();
		
		HttpSession session = request.getSession();
//		System.out.println("in orderlist:" + session.getId());
		String loginedId = (String)session.getAttribute("loginedId");
		Map<String, Object> map = new HashMap<>();
		if(loginedId == null) { //로그인 안된 경우
			map.put("status", 0);
			map.put("msg", "로그인하세요");
		}else {
			try {
				List<OrderInfo> list = service.findById(loginedId);
				map.put("status", 1);
				map.put("list", list);				
			} catch (FindException e) {
				e.printStackTrace();
				map.put("status", 0);
				map.put("msg", e.getMessage());
			}			
		}
		out.print( mapper.writeValueAsString(map));
		return null;
	}

}
