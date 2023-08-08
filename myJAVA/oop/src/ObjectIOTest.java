import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.my.customer.dto.Customer;

public class ObjectIOTest {
	
	public static void write() {
		
		/*
		 * 스트림: 바이트 단위 출력 스트림
		 * 필터 스트림: 객체 단위 출력
		 * 목적지: 파일
		 */
		
		String fileName = "a.ser";
		
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			
			// java.io.NotSerializableException 발생
			// Serializable interface 구현을 안했기 때문에 예외 발생!
			Customer c = new Customer("sengna", "1216", "1997", "jjang");
			
			oos.writeObject(new Date()); // 얼리기
			oos.writeObject(c);			 // 얼리기
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
			
		} // try-catch-finally
		
	} // write()
	
	public static void read() {
		
		String fileName = "a.ser";
		
		ObjectInputStream ois = null;
		
		// 직접 자원과 만날 수 없음!
		// + 자원과 직접 만날 수 있는 Stream = NodeStream
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			
			// 파일 내용 읽기 - 객체 녹이기
			Object obj1 = ois.readObject();
			System.out.println("obj1.toString(): " + obj1.toString());
			// 얼린 시점의 시간값이 그대로 출력됨! 객체 녹일 때의 시간값 X
			
			Object obj2 = ois.readObject();
			System.out.println("obj2: " + obj2); // obj2.toString() 자동 호출됨!
			// [id=sengna, pwd=1216, name=null, address=null]
			// Customer 클래스의 부모인 Person의 멤버 변수인 name과 address는 직렬화 대상 X
			// Customer가 가지고 있는 id와 pwd만 직렬화가 진행됨!
			// 다 직렬화하고 싶다면 부모인 Person에 Serializable 구현해야 함! -> 자식인 Customer도 자동으로 직렬화됨
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // try-catch
			} // if
			
		} // try-catch-finally
		
	} // read()
	
	public static void main(String[] args) {
		
		write();
		read();
		
	} // end main
	
} // end class
