package control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;
import com.my.util.Attach;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	// 회원 가입
	@GetMapping("/signup")
	public Map<String, Object> signup(String id, String pwd, String name,
			HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			
			Attach attach = new Attach(request);

			// 아이디 중복 검사
			service.idDupChk(id);
			
			try {
				service.idDupChk(id);
				map.put("status", 0);
			} catch (FindException e) {
				map.put("status", 1);
			} // try-catch
			
			// 회원 가입
			Customer c = new Customer(id, pwd, name, null);
			
			service.signup(c);
			
			// 프로필 업로드
			try {
				String originProfileFileName = attach.getFile("f1").get(0).getName();
				attach.upload("f1", id+"_profile_" + originProfileFileName);
			} catch (Exception e) {} // inner-try-catch
			
			// 자기소개 업로드
			try {
				String originIntroFileName = attach.getFile("f2").get(0).getName();			
				attach.upload("f2", id+"_intro_" + originIntroFileName);	
			} catch (Exception e) {} // inner-try-catch
			
			map.put("status", 1);
			map.put("msg", "가입성공");
			
		} catch (Exception e) {
			
			map.put("status", 0);
			map.put("msg", e.getMessage());
			
		} // outer-try-catch
		
		return null;
		
	} // signup
	
	// 로그인 
	@PostMapping("/login")
	public Map<String, Object> login(String id, String pwd, HttpSession session) {
		
		Map<String, Object> map = new HashMap<>();
		
		session.removeAttribute("loginedId");
		
		try {
			service.login(id, pwd);
			
			map.put("status", 1);
			map.put("msg", "로그인 성공!");
		} catch (FindException e) {
			e.printStackTrace();
			
			map.put("status", 2);
			map.put("msg", "로그인 실패!");
		}
		
		return map;
		
	} // login()
	
	// 로그아웃
	@GetMapping("/logout")
	public void logout(HttpSession session) {
		
		session.removeAttribute("loginedId");
		session.invalidate();
		
	} // logout()
	
} // end class
