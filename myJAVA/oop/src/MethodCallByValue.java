
public class MethodCallByValue {
	
	public static void m(int i) {
		i = 99;
		
	} // m
	
	public static void m(int[] arr) {
		arr[0] = 99;
	} // m
	
	public static void main(String[] args) {
		int i = 1;
		m(i);
		System.out.println(i);
		
		int[] arr = {1, 2, 3};
		m(arr);
		System.out.println(arr[0]);
		
	} // main

} // end class
