class A {
	
	static int si;
	int i ;
	
	static void sm() {
		System.out.println(si);
	}
	
	void m() {
		System.out.println(this.i);
	}
	
} // A

public class StaticTest {

	public static void main(String[] args) {
		
		System.out.println(A.si);
//		System.out.println(A.i);

		A a1 = new A();
		A a2 = new A();
		
		System.out.println("a1.i = " + a1.i);
		System.out.println("a2.i = " + a2.i);
		System.out.println("a1.si = " + a1.si); // static변수를 사용할 때는 객체를 생성해서 찾아가지 말고!!
		System.out.println("a2.si = " + a2.si); // 위에 작성했던 것처럼 클래스이름.변수명으로 찾아가자!!
		
		a1.i++;
		a1.si++;
		
		System.out.println("a1.i = " + a1.i);	//1
		System.out.println("a1.si = " + a1.si);	//1
		System.out.println("A.si = " + A.si);	//1
		
		System.out.println("a2.i = "+a2.i);		//0
		System.out.println("a2.si = "+a2.si);	//1
		
	} // main

} // end class
