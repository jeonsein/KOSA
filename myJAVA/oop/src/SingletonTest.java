class Single{ 
	
	private static Single s = new Single();
	
	private Single() {

	}
	
	public static Single getInstance() {
		// return null;
		return s; // getInstnace메서드가 호출될 때 마다 Single이라는 객체가 생성되어 반환
	}
	
}

public class SingletonTest {

	public static void main(String[] args) {
		
		Single s1;
		Single s2;
//		s1 = new Single();
		
		s1 = Single.getInstance();		//OK	s1,s2의 참조하는 객체가 다르다!
		s2 = Single.getInstance();		//OK	무조건 한개의 객체로만 사용 하려면?
		System.out.println(s1 == s2);


	} // main
 
} // end class
