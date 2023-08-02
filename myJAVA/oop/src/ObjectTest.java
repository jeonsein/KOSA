public class ObjectTest {
	
	public static void main(String[] args) {
		

		Object o1 = new Object();
		Object o2 = new Object();
		Object o3;
		
		o3 = o1;
		
		System.out.println(o1.hashCode());
		System.out.println(o2.hashCode());
		System.out.println(o3.hashCode());
		
		
		System.out.println(o1 == o2);
		System.out.println(o1 == o3);
		
		System.out.println(o1.equals(o2));
		System.out.println(o1.equals(o3));
		
		Circle c1, c2;
		c1 = new Circle(5);
		c2 = new Circle(5);
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		
	} // main

} // end class
