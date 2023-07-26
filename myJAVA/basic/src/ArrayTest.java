public class ArrayTest {
	public static void main(String[] args) {

		int[] arr;
	
		arr = new int[4];
		
		arr[0] = 10; // 국어 점수
		arr[1] = 20; // 수학 점수
		arr[2] = 33; // 영어 점수
		arr[3] = 40; // 과학 점수
		
		System.out.println(arr.length); // 4
	
		int totalScore = 0;
//		totalScore += arr[0];
//		totalScore += arr[1];
//		totalScore += arr[2];
		
		int length = arr.length; // 과목수

//		for(int i = 0; i < length; i++) {
//			totalScore += arr[i];
//		} // for
		// 🔽 동일 코드
		
		for(int score : arr) {
			totalScore += score;
		} // enhanced for
		
		float avg = (float)totalScore / length;
		System.out.println("totlaScore = " + totalScore + " , avg = " + avg);
		
	} // main
} // end class
