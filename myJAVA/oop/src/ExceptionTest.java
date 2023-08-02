import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {
	
	public static void m(Object obj) {
		
		try {
			String str = obj.toString();
			
			System.out.println("객체의 정보: " + str);			
		} catch(NullPointerException e) {
			System.out.println("e.getMessage(): " + e.getMessage());
		} finally {
			System.out.println("finally");
		} // try-catch-finally
		
	} // m(Object obj)
	
	public static void m(int num) {
		
		try {
			// 예외 발생 지점
			System.out.println("99를 " + num + 
					"로 나눈 나머지 값은: " + (99%num) );
		} catch(ArithmeticException e) {
//			e.printStackTrace();
			String msg = e.getMessage();
			System.out.println("e.getMessage(): " + msg);
		} // try-catch
	
	} // m(int num)
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("0이 아닌 숫자값을 입력하세요: ");
		
		int num;
		
		try {

			num = sc.nextInt();
			
			if(num == 0) {
				System.out.println("0을 입력했습니다. 자동으로 1로 설정됩니다.");
				
				num = 1;
				
			} // if
			
		} catch (InputMismatchException e) {
			e.printStackTrace();
			
//			System.out.println("숫자를 입력하세요.");
			System.out.println("숫자값을 입력하지 않았습니다. 자동으로 1로 설정됩니다.");
			
			num = 1;
			
//			System.out.println("0을 입력했습니다. 자동으로 1로 설정됩니다.");
//			m(1);
			
//			e.printStackTrace();
		} // try-catch
		
//		if(num == 0) {
//			System.out.println("0을 입력했습니다. 자동으로 1로 설정됩니다.");
//			
//			return;
//		}
//		
		m(num);
		
		Object o;
		o = new Object();
		
		m(o);
		
		o = null;
		
		m(o);
		
		
		// 강사님
//		try {
//			m(num);			
//		} catch (ArithmeticException e) {
//			System.out.println("0은 입력하실 수 없습니다.");
//		} // try-catch
		
	} // end main
	
} // end class
