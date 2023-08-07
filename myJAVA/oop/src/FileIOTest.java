import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileIOTest {

	public static void read() {
		/*
		 * 스트림 종류: (byte 단위)입력 스트림
		 * 자원: 파일
		 */
		
		// \ 하나만 쓰면 escape sequence로 이해하기 때문에 2개 쓰기!
		String fileName = "D:\\a.txt";
		
		log.info("FileInputStream 사용 🔽🔽🔽🔽");
		// #FileInputStream
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(fileName); // 자원 연결
			
			int readValue = -1;
			
			while( (readValue = fis.read()) != -1 ) {
				log.info((char)readValue);
//				System.out.print((char)readValue);
			} // while
			
			fis.close(); // 자원 연결 해제

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();// 자원 연결 해제
				} catch (IOException e) {
					e.printStackTrace();
				} //try-catch
			} // if
		} // Multi try-catch -> 부모 Exception이 아래로!!
		
//		-------------------------------------
		System.out.println("----------------------------------------------");
		log.info("FileReader 사용 🔽🔽🔽🔽");
		
		/*
		 * 스트림 종류: (문자 단위)입력 스트림
		 * 자원: 파일
		 */
		
		// #FileReaderStream
		FileReader fr = null; // 초기화 문제 해결
		
		try {
			fr = new FileReader(fileName); // 자원 연결
			
			int readValue = -1;
			
			while( (readValue = fr.read()) != -1 ) {
				log.info((char)readValue);
//				System.out.print((char)readValue);
			} // while

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // 안정적인 close 작업! -> NPE 발생 없애기 위한 방법
			if(fr != null) {
				try {
					fr.close();// 자원 연결 해제
				} catch (IOException e) {
					e.printStackTrace();
				} //try-catch
			} // if
		} // try-catch-finally
	
	} // read()
	
	public static void write() {
		
		/*
		 * 스트림 종류: (byte 단위)출력 스트림 
		 * 목적지: 파일
		 */
				
		/*
		String fileName = "D:\\b.txt";
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(fileName);
			
//			fos.write(65); // A
			
			byte[] bytes = "SENGNA".getBytes();
			fos.write(bytes); // SENGNA
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // Multi try-catch
		*/

//		-------------------------------------
		
		/*
		 * 스트림 종류: (문자 단위)출력 스트림 
		 * 목적지: 파일
		 */
		
		String fileName = "D:\\c.txt";
		
		FileWriter fw = null;
		
		try {
//			fw = new FileWriter(fileName);
			fw = new FileWriter(fileName, true); 
			// 기존 파일이 존재할 경우, 기존 파일 끝에 write할 내용을 uppend!
			// 기존 파일이 없을 경우, 새로 파일 생성!
			// 셍나는 짱이다!셍나는 짱이다!
			
			fw.write("셍나는 짱이다!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				// 내부 버퍼에서 출력 안해주면 파일에 내용 없음 -ㅇ-
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
		} // try-catch-finally
		
	} // write()
	
	public static void main(String[] args) {

//		read();
		write();
	
	} // end main
	
} // end class
