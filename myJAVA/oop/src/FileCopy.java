import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCopy {

	public static void main(String[] args) {
		
		/*
		 * oop> java -cp bin FileCopy
		 * 복사할 원본 파일명을 입력하세요: D:\\a.txt
		 * 붙여넣기 할 파일명을 입력하세요: D:\\acopy.txt
		 * 원본 파일이 없는 경우 "복사할 원본파일이 없습니다" 메시지 출력
		 * << byte 단위로 읽고 쓰기 >>
		 */

		Scanner sc = new Scanner(System.in);
		
		// 입력 받은 문자열 앞에 경로 추가
		String prefix = "D:\\";
		
		System.out.println("복사할 원본 파일명을 입력하세요: ");
		String originalFileName = sc.nextLine(); // "D:\\a.txt"
		originalFileName = prefix + originalFileName;
		
		System.out.println("붙여넣기 할 파일명을 입력하세요: ");
		String targetFileName = sc.next(); // "D:\\acopy.txt";
		targetFileName = prefix + targetFileName;
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(originalFileName);
			fos = new FileOutputStream(targetFileName);
			
			int readValue;
			byte[] readBytes = new byte[100]; // 크기를.. 몇으로 둬야할지..
			
			while( (readValue = fis.read(readBytes)) != -1 ) { // byte 수만큼 반복
//				fos.write(readBytes, 0, readValue);
				// write(String string, int startingIndex, int lengthOfstring)
				fos.write(readValue);
			} // while
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if(fos != null) {
				try {					
					// flush + 자원 해제
					fos.close();
					fis.close();
				} catch(IOException e) {
					e.printStackTrace();
				} // try-catch
			} else {
				System.out.println("복사할 원본 파일이 없습니다.");
			} // if-else
			
		} // try-catch-finally
		
	} // end main
	
} // end class
