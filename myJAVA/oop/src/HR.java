// 제공자

import java.util.HashMap;

class Employee {
	String no;
	String name;
	int salary;
	
	HashMap<String, String> map = new HashMap<String, String>();
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	void print() {
		System.out.println("사번: " + no);
		System.out.println("사원명: " + name);
		System.out.println("기본급: " + salary);
		System.out.println("");
	}

} // end class

// 사용자
public class HR {
	public static void main(String[] args) {
		
		Employee e1 = new Employee();
		
		String no = e1.no;
//		String name = e1.name;
//		String salary = e1.salary;
		
		System.out.println("e1.no = " + e1.no); // null
		
		e1.setNo("2301");		// 사번
		e1.setName("셍나");			// 사원명
		e1.setSalary(1000000000);		// 기본급
		
		System.out.println("사원의 사번: " + e1.getNo());
		System.out.println("사원의 이름: " + e1.getName());
		System.out.println("사원의 기본급: " + e1.getSalary());
		System.out.println("");
		
		e1.print();
		
		Employee eTemp;
//		eTemp = null; // 초기화
//		eTemp = new Employee(); // 초기화
		eTemp = e1; // 초기화 
		
		System.out.println(e1 == eTemp); // true
		eTemp.setSalary(2000);
		System.out.println("e1.getSalary(): " + e1.getSalary());
		eTemp = null; // eTemp = e1의 참조관계를 끊음
		System.out.println(e1 == eTemp); // false
		System.out.println("eTemp: " + eTemp); // null
		System.out.println(e1.getSalary()); // 2000+
//		System.out.println(eTemp.getSalary()); // NPE 발생 후 프로그램 종료
		
//		---------------------------------
		
		Employee[] employees; // 선언
		employees = new Employee[20];
		employees[0] = e1;
		
		for(int i = 2; i <= 5; i++) {

			Employee e2 = new Employee();
			e2.setNo("230"+i); e2.setName("name"+i); e2.setSalary(1000);
			employees[i-1] = e2;
			
		} // for
		
		for(int i = 0; i < 5; i++) {
//		for(int i = 0; i < employees.length; i++) { // NPE
			employees[i].print();
		}
		
		
	} // end main
} // end class
