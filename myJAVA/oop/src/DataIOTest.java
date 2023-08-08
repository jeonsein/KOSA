import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class DataIOTest {
	
	public static void write() {
		
		/*
		 * 스트림: 바이트 단위 출력 스트림
		 * 필터 스트림: 데이터 타입 단위 출력 스트림
		 * 목적지: 파일
		 */
		
		String directoryPath = "D:/attache/"; // 저장 위치
		// 저장 위치 셋팅 안해주면
		// C:\KOSA202307\myJAVA\oop 여기에 저장댐!
		
		String fileName = "a.dat"; // 확장자는 뭐든 상관 X
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
	    try {
	        File directory = new File(directoryPath);
	        
	        if (!directory.exists()) {
	            directory.mkdirs(); // 디렉토리가 없으면 생성
	        } // if
	        
	        fos = new FileOutputStream(directoryPath + fileName);
	        dos = new DataOutputStream(fos);
	        
	        dos.writeInt(1);
	        dos.writeFloat(12.16F);
	        dos.writeBoolean(false);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {

	        if(dos != null) {
	            try {
	                dos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } // try-catch
	        } // if
	        
	    } // try-catch-finally
		
	} // write()
	
	/* 강사님 write() 코드
	public static void write() {

		String fileName = "a.dat";
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			dos.writeInt(1);
			dos.writeFloat(2.3F);
			dos.writeBoolean(false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	*/
	
	public static void read() {

		/*
		 * 스트림: 바이트 단위 입력 스트림
		 * 필터 스트림: 데이터 타입 단위 입력 스트림
		 * 자원: 파일
		 */
		
		String fileName = "a.dat";
		
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			
			int i = dis.readInt();
			float f = dis.readFloat();
			boolean b = dis.readBoolean();
			
			System.out.println(i + ":" + f + ":" + b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 자원 생성 순서에 따라 close! 연결 해제는 거꾸로 해줌!
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
			
		} // try-catch-finally
		
	} // read()
	
	public static void main(String[] args) {
		
//		write();
		read();
		
	} // end main
	
} // end class
