package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.my.customer.dto.Customer;
import com.my.customer.service.CustomerService;
import com.my.exception.FindException;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	// 아이디 중복 검사
	@GetMapping("/iddupchk")
//	public void idDupCk(String id) {
	public ResponseEntity<?> idDupCk(String id) {
		// Map<String, Object> map = new HashMap<>();

		try {
			service.idDupChk(id);
//			map.put("status", 0);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html;charset=UTF-8");
			
			return new ResponseEntity<> ("이미 사용중인 아이디입니다 :-(", headers, HttpStatus.BAD_REQUEST);
		} catch (FindException e) {
//			map.put("status", 1);
			return new ResponseEntity<> (HttpStatus.OK);
		} // try-catch
		
	} // idDupCk()

	// 회원 가입
	@PostMapping("/signup")
	public Map<String, Object> signup(String id, String pwd, String name,
			HttpServletRequest request, HttpServletResponse response,
			MultipartFile f1, MultipartFile f2) throws Exception {

		Map<String, Object> map = new HashMap<>();

//		try {
			
			// 회원 가입
			Customer c = new Customer(id, pwd, name, null);

			service.signup(c);

			// 프로필 업로드
//			try {
				
				// 파일 사이즈 유효성 검사
				if(f1 != null  && f1.getSize() > 0) {
					
					String originProfileFileName = id + "_profile_" + f1.getOriginalFilename();
					
					// (디렉토리명, 파일명)
					File targetFile = new File("C:\\KOSA202307\\attaches" , originProfileFileName);
					
					// 스프링에서 제공되는 utility
					// 원본 파일의 내용을 복사 붙여넣기 해줄 수 있음
					FileCopyUtils.copy(f1.getBytes(), targetFile); // (바이트배열, 저장경로)
					// => f1 파일 크기를 원본으로 삼아서 targetFile에 저장
					
					// ---- 섬네일파일 만들기 START ----
					int width = 100;
					int height = 100;				

					String thumbFileName = "t_" + id + "." + FilenameUtils.getExtension(f1.getOriginalFilename()); //섬네일파일명
					File thumbFile = new File("C:\\KOSA202307\\attaches" , thumbFileName);
					
					FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);//출력스트림
					InputStream thumbnailIS = f1.getInputStream(); //첨부파일 입력스트림	
					
					Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
					// ----- 섬네일파일 만들기 END ------
					
				} else {
					throw new Exception();
				} // if-else

//			} catch (Exception e) {} // inner-try-catch

			// 자기소개 업로드
//			try {

				if(f2 != null  && f2.getSize() > 0) {

					String originIntroFileName = id + "_intro_" + f2.getOriginalFilename();
					
					File targetFile = new File("C:\\KOSA202307\\attaches" ,originIntroFileName);
					FileCopyUtils.copy(f2.getBytes(), targetFile);

				} else {
					throw new Exception();
				} // if-else

//			} catch (Exception e) {} // inner-try-catch

			map.put("status", 1);
			map.put("msg", "가입성공");

//		} catch (Exception e) {
//
//			map.put("status", 0);
//			map.put("msg", e.getMessage());
//
//		} // outer-try-catch

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
			
			session.setAttribute("loginedId", id);
		} catch (FindException e) {
			e.printStackTrace();

			map.put("status", 2);
			map.put("msg", "로그인 실패!");
		} // try-catch

		return map;

	} // login()

	// 로그아웃
	@GetMapping("/logout")
	public void logout(HttpSession session) {

		session.removeAttribute("loginedId");
		session.invalidate();

	} // logout()

} // end class
