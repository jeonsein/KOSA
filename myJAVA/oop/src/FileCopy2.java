/**
 *  교수님 FileCopy_ver
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCopy2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("원본 파일명: ");
		String originFileName = sc.nextLine();
		
		System.out.println("복제본 파일명: ");
		String copyFileName = sc.nextLine();
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(originFileName);
		} catch (FileNotFoundException e) {	// 원본 파일명 없는 경우 발생
			System.out.println("원본 파일이 없습니다.");
//			e.printStackTrace();
			
			return; // 원본 파일이 없기 때문에 진행할 것이 X -> return으로 끝!
		} // try-catch
		
		try {
			fos = new FileOutputStream(copyFileName);
		} catch (FileNotFoundException e) { // if 경로가 없을 경우 발생
			e.printStackTrace();
			
			return;
		} // try-catch
		
		// 원본 읽기
		int readValue; 
		
		try {
			
			while( (readValue = fis.read()) != -1 ) {
				fos.write(readValue);
			} // while
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
			
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} // try-catch
			
		} // try-catch-finally
		
		// 안전한 예외처리 위해서 각각 다 예외처리 해줌!
		
	} // end main

} // end class
