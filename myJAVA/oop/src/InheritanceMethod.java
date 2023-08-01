class ParentMethod1 {
	void pm1() {
		System.out.println("ParentMethod1의 pm1()입니다.");
	}
	void m() {
		System.out.println("ParentMethod1의 m()입니다.");
	}
}

class ParentMethod2 extends ParentMethod1 {
	void pm2() {
		System.out.println("ParentMethod1의 pm2()입니다.");
	}
}

class ChildMethod extends ParentMethod2 {
	void cm() {
		System.out.println("ParentMethod1의 cm()입니다.");
	}
	void m() {
		System.out.println("ChildMethod의 m()입니다.");
		super.m();
	}
}

public class InheritanceMethod {

	public static void main(String[] args) {
		
		ChildMethod cm = new ChildMethod();
		cm.m(); // ChildMethod의 m()입니다
		cm.pm2();
		cm.pm1();
	
		System.out.println("===========================");
		
		ParentMethod2 pm2 = new ParentMethod2();
		pm2.m();
//		pm2.cm(); 자식이 갖고 있는 메서드는 호출 불가
		pm2.pm2();
		pm2.pm1(); 
		
	} // main

} // end class
