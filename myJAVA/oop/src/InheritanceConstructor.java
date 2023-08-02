class ParentConstructor {
	ParentConstructor() {
		System.out.println("ParentConstructor() 생성자가 호출됨!");
	}
	
	ParentConstructor(String name) {
		System.out.println("ParentConstructor(" + name + ") 생성자가 호출됨");
	}
}

class ChildConstructor extends ParentConstructor {
	ChildConstructor() {
		// super(); 가 생략됨!
		System.out.println("ChildConstructor() 생성자가 호출됨!");
	}
	ChildConstructor(int age) {
		// 부모의 특정 매개변수를 가지고 있는 생성자를 호출
		super("셍나");
		System.out.println("ChildConstructor(" + age + ") 생성자가 호출됨");
		// 🔽
		// ParentConstructor() 생성자가 호출됨!
		// ChildConstructor(10) 생성자가 호출됨
	}
}

public class InheritanceConstructor {
	public static void main(String[] args) {
		ParentConstructor p = new ParentConstructor();
		ChildConstructor c = new ChildConstructor();
		// 🔽 부모 먼저 생성!
		// ParentConstructor() 생성자가 호출됨!
		// ChildConstructor() 생성자가 호출됨!
		
		ChildConstructor c1 = new ChildConstructor(10);
		
	}
}
