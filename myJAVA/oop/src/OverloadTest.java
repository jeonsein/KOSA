public class OverloadTest {

	public static void plus(int a, int b) {
		
		System.out.println("합은 : " + ( a + b ));
		
	} // plus
	
	public static void plus(int a, int b, int c) {
		
		System.out.println("합은 : " + ( a + b + c ));
		
	} // plus
	
	public static void plus(double a, double b) {
		// 2.0은 인자값과 매개변수의 타입이 같은데 1은 자료형이 일치하지 않는다
		// 전달될때는 1인데 a는 1.0이 되면서 자동 형변환 되어 전달된 것!
		
		System.out.println("합은 : " + ( a + b ));
		
	} // plus
	
	public static void main(String[] args) {
		
		plus(1, 2);
		plus(1, 2, 3);
		plus(1, 2.0);

	} // main

} // end class
