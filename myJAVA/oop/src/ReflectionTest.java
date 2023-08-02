import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReflectionTest {
	
	public static void test(String className) throws ClassNotFoundException {
		
//		String s;
//		Date d;
		
//		Class.forName("java.util.Date");
		
		// 메소드에게 클래스 이름만을 전달하기!
		Class c = Class.forName(className);
		Field[] fields = c.getDeclaredFields();
		
		for(Field f : fields) {
			System.out.println("f.getName(): " + f.getName());
		} // for
		
		System.out.println(" ------------ ");
		
		Method[] methods = c.getDeclaredMethods();
		
		for(Method m : methods) {
			System.out.println("m.getName(): " + m.getName());
		} // for
		
		try {
//			c.newInstance(); // 🔽
			Object obj = c.getDeclaredConstructor().newInstance();
			
			System.out.println("obj: " + obj); // 인자인 obj의 toString() 자동 호출
		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch
		
	} // test()
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("클래스 이름을 입력하세요! \n ex) java.util.Date:");
		
		String className = sc.nextLine();
		
		test(className);
		
	} // end main

} // end class

// 1. ReflectionTest.class 찾기
// 2. JVM으로 로딩 (실행 시 로드하는 것 -> Load time Dynamic Load)
// 3. 바이트 코드 검색
// 4. 0,1 재해석, Method 영역
// 5. static 변수 자동 초기화
// 6. main() 호출