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
		
//		------------------------------
		
		int[][] arrTwo = new int[3][2];
		int num = 1;
		
//		arrTwo[0][0]=1;
//		arrTwo[0][1]=2;
//		for(int col = 0; col < 2; col++, num++) {
//			arrTwo[0][col] = num;
//		} // for
//		arrTwo[0][0]=3;
//		arrTwo[0][1]=4;
//		for(int col = 0; col < 2; col++, num++) {
//			arrTwo[1][col] = num;
//		} // for
//		arrTwo[0][0]=5;
//		arrTwo[0][1]=6;
//		for(int col = 0; col < 2; col++, num++) {
//			arrTwo[2][col] = num;
//		} // for
		
//		🔽 동일 기능
		int rowLength = arrTwo.length; // 3

		for(int row = 0; row < rowLength; row++) {
			int colLength = arrTwo[row].length; // 2
			
			for(int col = 0; col < colLength; col++, num++) {
				arrTwo[row][col] = num;
			} // inner-for
		} // outter-for
		
		System.out.println(arrTwo);
		for(int row = 0; row < rowLength; row++) {
			int colLength = arrTwo[row].length; // 2
			
			for(int col = 0; col < colLength; col++) {
				System.out.print(arrTwo[row][col] + ",");
			} // inner-for
			System.out.println();
		} // outter-for
		
//		------------------------------
		
		int [][] arrTwo2 = new int[3][]; // 행별 열수를 다르게 하기
		arrTwo2[0] = new int[1];
		arrTwo2[1] = new int[2];
		arrTwo2[2] = new int[3];
		
		System.out.println(arrTwo.length); // 3
		System.out.println(arrTwo2[0].length); // 1
		System.out.println(arrTwo2[1].length); // 2
		System.out.println(arrTwo2[2].length); // 3
		
//		arrTwo2[0][0] = 1;
//		
//		arrTwo2[1][0] = 2;
//		arrTwo2[1][1] = 3;
//		
//		arrTwo2[2][0] = 4;
//		arrTwo2[2][1] = 5;
//		arrTwo2[2][2] = 6;
		
		num = 1;
		rowLength = arrTwo2.length;
		for(int row=0; row< rowLength ; row++) {
			int colLength = arrTwo2[row].length;
			for(int col=0; col<colLength; col++, num++) {
				arrTwo2[row][col] = num;
			} // inner-for
		} // outter-for
		
		for(int row=0; row< rowLength ; row++) {
			int colLength = arrTwo2[row].length;
			for(int col=0; col<colLength; col++) {
				System.out.print(arrTwo2[row][col] + ",");
			} // inner-for
			System.out.println();
		} // outter-for
		
//		------------------------------
		
		int []arr1 = {1, 10, 6, 3, 2, 3, 6, 1, 5, 7, 6, 5};
		
		System.out.println("숫자의 출현 횟수를 출력하세요.");
//		System.out.println("ex) 1의 출현 횟수는 : 2회입니다.");
		
		
	} // main
} // end class
