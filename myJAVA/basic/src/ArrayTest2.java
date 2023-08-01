import java.util.Arrays;

public class ArrayTest2 {

	public static void main(String[] args) {
		
		int[] arr = {11, 33, 44, 55, 22};
		Arrays.sort(arr);
		
		System.out.println(arr);  // 그냥 찍으면 주소값나옴
		for(int i : arr) {
			System.out.print("["+i+"]");
		} // for
		
		System.out.println();
		
		// 일부분 정렬
		int[] arr1 = {22, 11, 44, 55, 33};
		Arrays.sort(arr1, 2, 4);
		
		for(int i : arr1) {
			System.out.print("["+i+"]");
		}
		
		System.out.println();
		
		// 버블정렬
		
		int[] arr2 = {5, 3, 4, 2, 6, 7, 1, 8};
		int temp = 0;
		
		for(int i = 0; i < arr2.length; i++) {
			for(int j = i+1; j < arr2.length; j++) {
				if( arr2[i] > arr2[j]) {
					temp = arr2[i];
					arr2[i] = arr2[j];
					arr2[j] = temp;
				} // if
			} // for
		} // for
		
		for(int p2: arr2) {
			System.out.print("["+p2+"]");
		} // for
		
		

	} // main

} // end class
