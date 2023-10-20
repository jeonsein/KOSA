package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadDownloadController {

	// 파일 업로드 작업
	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile f1, List<MultipartFile> f2) throws IOException {
		
		System.out.println("파일명: " + f1.getOriginalFilename());
		System.out.println("파일 사이즈: " + f1.getSize());
		
		// 파일 사이즈 유효성 검사
		if(f1 != null  && f1.getSize() > 0) {
			// (디렉토리명, 파일명)
			File targetFile = new File("C:\\KOSA202307\\attaches" ,f1.getOriginalFilename());
			
			// 스프링에서 제공되는 utility
			// 원본 파일의 내용을 복사 붙여넣기 해줄 수 있음
			FileCopyUtils.copy(f1.getBytes(), targetFile); // (바이트배열, 저장경로)
			// => f1 파일 크기를 원본으로 삼아서 targetFile에 저장
			
			// ---- 섬네일파일 만들기 START ----
			int width = 100;
			int height = 100;				

			String thumbFileName = "t_" + f1.getOriginalFilename(); //섬네일파일명
			File thumbFile = new File("C:\\KOSA202307\\attaches" , thumbFileName);
			
			FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);//출력스트림
			InputStream thumbnailIS = f1.getInputStream(); //첨부파일 입력스트림	
			
			Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
			// ----- 섬네일파일 만들기 END ------
			
			// f2 전송 (여러 파일)
			for(MultipartFile mf : f2) {
			
				if(mf != null && mf.getSize() > 0) {
					File targetFile2 = new File("C:\\KOSA202307\\attaches" ,mf.getOriginalFilename());

					FileCopyUtils.copy(mf.getBytes(), targetFile2);
				} // if
			
			} // for
			
			return "Upload OK";
		} else {
			return "Upload FAIL";
		} // if-else
		
	} // upload()

	// --------------------------------------
	
	// 파일 다운로드 작업

	@GetMapping("/download")
	// <?> = 모든 자료형, 원래는 응답 내용의 자료형이 와야 함! 
	// 변경 사항을 고려하여 응답 내용의 자료형이 불분명할 때 사용하면 됨
	public ResponseEntity<?> download(HttpSession session, String id, String opt) throws IOException {
		
		String attachesDir = "C:\\KOSA202307\\attaches";
		File dir = new File(attachesDir);

		String fileName = id + "_"+ opt +"_";
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + fileName);
		
		HttpStatus status = HttpStatus.OK; // 200

		ResponseEntity entity = null;
		
		for(File file: dir.listFiles()) {
			String existFileName = file.getName();
			
			if(existFileName.startsWith(fileName)) {
				
				System.out.println(existFileName + "파일입니다. 파일크기: " + file.length());
				
				HttpHeaders headers = new HttpHeaders();
				
				// 헤더 설정
				headers.add(
						HttpHeaders.CONTENT_DISPOSITION, 
						"attachment;filename = " + URLEncoder.encode(existFileName, "UTF-8")
						);
				
				// path를 이용해서 probeContentType() 실행으로 파일의 형식을 얻어낼 수 있음
				String contentType = Files.probeContentType(file.toPath()); // 파일의 형식
				
				headers.add(HttpHeaders.CONTENT_TYPE, contentType); // 이후 파일의 형식을 응답 형식으로 사용
				headers.add(HttpHeaders.CONTENT_LENGTH, "" + file.length()); // 응답 길이 설정
				
				// 전제조건 = 파일이 존재함
				byte[]bArr = FileCopyUtils.copyToByteArray(file); // 파일 내용을 byte형태로 return 받음. 응답 내용이 됨
				
				entity = new ResponseEntity<>(bArr, headers, status);
				
				break;
				
			} // if
			
		} // for
		
		return entity;
		
	} // download()
	
} // end class