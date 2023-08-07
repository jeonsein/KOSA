import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SystemInTest {

	public static void main(String[] args) {
		
		// # InputStream
		InputStream is = System.in;
		
		
		/*
		try {
//			int readValue = is.read();
//			log.info("readValue = " + readValue); // a입력 -> 97
			
			int readValue = -1;
			
			// Windows의 스트림 종료
			// ctrl + z 누르면 is.read()의 값이 -1이 되어 반복문 탈출
			while( (readValue = is.read()) != -1 ) {
//				log.info("readValue: " + readValue);
				log.info("(char)readValue: " + (char)readValue);
			} // while
			// 🔽🔽
			// abc 입력 시, (이후에도 이어서 입력 가능)
			// readValue: 97
			// readValue: 98
			// readValue: 99
			// enter 🔽
			// readValue: 13
			// readValue: 10
		*/
		
		// InputStreamReader : InputStream -> Reader
		InputStreamReader isr = new InputStreamReader(is);
		
			try {
				int readValue = -1;
				
				// 문자 단위로 읽기
				while( (readValue = isr.read()) != -1 ) {
					log.info("readValue: " + readValue);
					log.info("(char)readValue: " + (char)readValue);
				} // while
			
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
		
	} // end main

} // end class
