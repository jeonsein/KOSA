package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/octet-stream;charset=UTF-8");

		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.21:5500");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		//		PrinterWriter out = response.getWriter();
		//		-> 문자 단위의 출력 스트림임! 현재는 바이너리로 파일 내용을 그대로 응답하고
		//		싶기 때문에 사용하지 않음!

		// 대신 출력 스트림으로 이걸 사용함!
		ServletOutputStream sos = response.getOutputStream();

		String id = request.getParameter("id");
		String opt = request.getParameter("opt");	

		// 파일 내용 자체가 그대로 응답되어야 하기 때문에 우선 파일을 찾아줌
		String attachesDir = "C:\\KOSA202307\\attaches";
		String fileName = id + "_" + opt + "_";

		File dir = new File(attachesDir);

		// 디렉토리에 내가 찾고자 하는 파일(fileName)이 있는지 탐색!
		for(File file : dir.listFiles()) {
			String existFileName = file.getName();

			// "id + _profile_"로 시작하는 파일이 있는지 비교
			if(existFileName.startsWith(fileName)) {
				System.out.println(existFileName + "파일입니다.");
				System.out.println("파일 크기: " + file.length());
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(existFileName, "UTF-8"));

				FileInputStream fis = new FileInputStream(file);
				int readValue = -1;

				while((readValue = fis.read()) != -1) {
					sos.write(readValue);
				}
				sos.close();
				return null;
			} // if
		} // enhanced-for
		System.out.println(id + "의 프로필 파일이 없습니다.");

		return null;
	} // execute()

} // end class
