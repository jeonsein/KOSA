public class First {
	
	static int smf; // static 필드
	int mf;			// non-static 필드

	public static void main(String[] args) {
		int lv; // 지역변수(local variable)
//		System.out.println("lv: " + lv); // 자동초기화 XXX
		
//		System.out.println("smf: " + smf); // 자동초기화 OOO 값은 0으로 나옴!
	
		// Cannot make a static reference to the non-static field mf
//		System.out.println("mf: " + mf);
		
		// 인스턴스화 (객체 만들기)
		First one; // 참조형 지역변수
		one = new First(); // 인스턴스화
		
		System.out.println(one.mf); // 0
		
	} // end main

} // end class
