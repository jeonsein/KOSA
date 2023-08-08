/**
 *  교수님 FileCopy_ver
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
		
		
	} // end main

} // end class
