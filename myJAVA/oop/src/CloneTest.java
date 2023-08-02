class Copy implements Cloneable { // extends Object
	
	int i;
	int []arr = {1, 2, 3};
	
	Object copy() {
		Object obj = null; // 참조변수이기 때문에 초기값으로 null
		
		try {
			// 객체 복제
			obj = this.clone();
			
			// 배열 복제
			int[] arrCopy = (int[])arr.clone();
			
			// 복제본에 배열 대입
			Copy copy = (Copy)obj ; 
			copy.arr = arrCopy;
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} // try-catch
		
		return obj;
	} // copy()
	
} // end class

// ----------------------------

public class CloneTest {

	public static void main(String[] args) {

		// 원본 객체
		Copy origin = new Copy();
		
		// 원본 값 설정
		origin.i = 9;
		origin.arr[0] = 9;
		
//		origin.clone();
		Object obj = origin.copy();
		Copy copy = (Copy)obj; // 원래 타입으로 형변환
		
		System.out.println("복제본 객체의 i 변수 값: " + copy.i); // 9
		System.out.println("복제본 객체의 arr[0] 변수 값: " + copy.arr[0]); // 9
		
		// 복제본 값 변경
		copy.i = 7;
		copy.arr[0] = 7;
		
		System.out.println("원본 객체의 i 변수 값: " + origin.i); // 9
		
		// 7 : shallow copy, 9: deep copy
		System.out.println("원본 객체의 arr[0] 변수 값: " + origin.arr[0]);
		
	} // end main
	
} // end class
