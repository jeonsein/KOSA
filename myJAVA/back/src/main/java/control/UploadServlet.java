package control;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 헤더 설정
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		String tempDir = "C:\\KOSA202307\\temp";		// 임시 파일 저장 경로
		String attachesDir = "C:\\KOSA202307\\attaches"; // 첨부 파일이 실제로 저장될 경로
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		File repository = new File(tempDir); // 해당 경로에 해당하는 파일 객체를 생성 후
		if(!repository.exists()) {	// repository에 해당하는 경로가 존재하는지 확인! 조건 = 없으면!!
			if(repository.mkdir()) {	// 해당 폴더가 없을 경우 만들어라
				System.out.println(tempDir + " 폴더 생성");
			} else {
				System.out.println(tempDir + " 폴더 생성 안됨");
				return;
			} // if-else
		} // if
		
		if(!new File(attachesDir).exists()) { // 존재 안하면 폴더 만들고
			if(new File(attachesDir).mkdir()) {
				System.out.println(attachesDir + " 폴더 생성");
			} else {
				System.out.println(attachesDir + " 폴더 생성 안됨");
				return;
			} // if-else
		} // if
		
		fileItemFactory.setRepository(repository); // 업로드 경로 설정을 세팅해줌!
		fileItemFactory.setSizeThreshold(10*1024); // 10*1024byte 이상인 경우 임시 파일이 만들어짐
		
		// 파일 업로드용 라이브러리를 사용하기!
		// 매개변수로 사용할 인자의 자료형은 fileItemFactory이기 때문에
		// 해당 객체를 생성하고 첨부 파일의 경로를 설정해준 것임!
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		
		// 요청을 해당 라이브러리에서 파싱함!
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			
			for(FileItem item : items) {	// 아이템을 꺼내오자!
				
				if(item.isFormField()) { // 요청시 전달되는 데이터인 경우
					System.out.println(item.getFieldName() + " : " + item.getString());
					// 						요청전달데이터이름			값
				} else { // 첨부파일인 경우
					System.out.println(item.getName() + " : " + item.getSize());
					
					// 파일 용량을 여기서 결정할 수 있음! 
					// 파일 용량이 너무 크면 업로드 못하게 할 수 있다는 의미
					if(item.getSize() > 0) {
						UUID uuid = UUID.randomUUID(); // 중복되지 않는 일련 번호
						//							해당 경로에	 해당 이름으로 저장
						File attacheFile = new File(attachesDir, uuid + "_" + item.getName());
						
						try {
							item.write(attacheFile); // 첨부파일을 서버 경로에 저장함!
							// 한 파일에서 쓰기 실패해도 다음 파일은 쓰기가 가능
						} catch (Exception e) {
							e.printStackTrace();
						} // try-catch
				
					} // if
					
				} // if-else
				
			} // for
		} catch (FileUploadException e) {
			e.printStackTrace();
		} // try-catch
		
//		---------------------------------
		
		/* 라이브러리 사용 전
		// post 방식의 요청인 경우 요청 바디의 default 형식은 
		// application/x-www-form-urlencoded 임!
//		String tValue = request.getParameter("t");
//		String f1Value = request.getParameter("f1");
		
		// post 방식의 요청인 경우 요청 바디의 형식은 
		// multi-part/form-data인 경우 Stream으로 읽어와야 함@!
		ServletInputStream sis =  request.getInputStream();
		
		Scanner sc = new Scanner(sis);
		
		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		} // while
		*/
		
	} // doPost()

} // end class
