import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileIOTest {

	public static void main(String[] args) {

		/*
		 * 스트림 종류: (byte 단위)입력 스트림
		 * 자원: 파일
		 */
		
		// \ 하나만 쓰면 escape sequence로 이해하기 때문에 2개 쓰기!
		String fileName = "D:\\a.txt";
		
		log.info("FileInputStream 사용 🔽🔽🔽🔽");
		// #FileInputStream
		FileInputStream fis;
		
		try {
			fis = new FileInputStream(fileName);
			
			int readValue = -1;
			
			while( (readValue = fis.read()) != -1 ) {
				log.info((char)readValue);
//				System.out.print((char)readValue);
			} // while

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // Multi try-catch -> 부모 Exception이 아래로!!
		
//		-------------------------------------
		System.out.println("----------------------------------------------");
		log.info("FileReader 사용 🔽🔽🔽🔽");
		
		/*
		 * 스트림 종류: (문자 단위)입력 스트림
		 * 자원: 파일
		 */
		
		// #FileReaderStream
		FileReader fr;
		
		try {
			fr = new FileReader(fileName);
			
			int readValue = -1;
			
			while( (readValue = fr.read()) != -1 ) {
				log.info((char)readValue);
//				System.out.print((char)readValue);
			} // while

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // Multi try-catch -> 부모 Exception이 아래로!!
		
	} // end main
	
} // end class
