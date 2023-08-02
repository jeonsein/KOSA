class PVariable1 {
	int a;
	
	void pm1() {
		System.out.println("PVariable1의 a=" + a);
	}
}

class PVariable2 extends PVariable1 {
	void pm2() {
		System.out.println("PVariable2의 a=" + a);
	}
}

class CVariable3 extends PVariable2 {
	String a;
	
	void cm() {
		System.out.println("CVariable3의 a=" + a + ", PVariable1의 a=" + super.a);
	}
}

public class InheritanceVariable {

	public static void main(String[] args) {
		
		PVariable1 pv1 = new PVariable1();
		pv1.a = 9;
		
		PVariable2 pv2 = new PVariable2();
		pv2.a = 99;
		
		CVariable3 cv = new CVariable3();
		cv.a = "찬돌";
		
		cv.cm();
		cv.pm2(); // cv.pm2를 호출 하는 것이니까 0이 출력됨
		cv.pm1(); // cv.pm1을 호출하는 것이니까 0 이 출력됨
		System.out.println("==================");
		pv2.pm2();
		pv2.pm1();
		System.out.println("==================");	
		pv1.pm1();
		
	} // main

} // end class
