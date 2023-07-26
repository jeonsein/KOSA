import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JScrollBar;

public class ArrayTest {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

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

		for (int score : arr) {
			totalScore += score;
		} // enhanced for

		float avg = (float) totalScore / length;
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

		for (int row = 0; row < rowLength; row++) {
			int colLength = arrTwo[row].length; // 2

			for (int col = 0; col < colLength; col++, num++) {
				arrTwo[row][col] = num;
			} // inner-for
		} // outter-for

		System.out.println(arrTwo);
		for (int row = 0; row < rowLength; row++) {
			int colLength = arrTwo[row].length; // 2

			for (int col = 0; col < colLength; col++) {
				System.out.print(arrTwo[row][col] + ",");
			} // inner-for
			System.out.println();
		} // outter-for

//		------------------------------

		int[][] arrTwo2 = new int[3][]; // 행별 열수를 다르게 하기
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
		for (int row = 0; row < rowLength; row++) {
			int colLength = arrTwo2[row].length;
			for (int col = 0; col < colLength; col++, num++) {
				arrTwo2[row][col] = num;
			} // inner-for
		} // outter-for

		for (int row = 0; row < rowLength; row++) {
			int colLength = arrTwo2[row].length;
			for (int col = 0; col < colLength; col++) {
				System.out.print(arrTwo2[row][col] + ",");
			} // inner-for
			System.out.println();
		} // outter-for

//		------------------------------
		
		// 횟수 카운트
		int[] arr1 = { 1, 10, 6, 3, 2, 4, 5, 5, 2, 1 };
		int[] arr2 = new int[10]; // [0]는 1의 출현횟수 누적공간
									// [1]는 2의 출현횟수 누적공간

		System.out.println("숫자의 출현횟수를 출력하시오.");
//			arr2[arr1[0]-1]++;
//			arr2[arr1[1]-1]++;
//			arr2[arr1[2]-1]++;
//			arr2[arr1[3]-1]++;

		// enhanced for
//			for(int value : arr1) {
//				arr2[ value-1]++;
//			}

		for (int i = 0; i < arr1.length; i++) {
			arr2[arr1[i] - 1]++; // 누적횟수 획득
		} // for
		
		for (int i = 0; i < arr2.length; i++) {
			System.out.println((i + 1) + "의 출현횟수 = " + arr2[i] + "회");
		} // for
		
//		------------------------------
		
		// 최대값
//		int[] arr3 = {5, 4, 7, 1, 2};
		int[] arr3 = {-1, -2, -3};
		System.out.println("최대값을 계산하시오.");

		int max = arr3[0];
		
		for(int i = 0; i < arr3.length; i++) {
			
			if( arr3[i] > max ) {
				max = arr3[i];
			} // if
		} // for
		
		System.out.println("최대값은 " + max + "입니다.");
		
//		------------------------------
		
		// 정렬하기 - 혼자 해보기!
		int[] arr4 = {5, 4, 7, 1, 2};

//		------------------------------
		
		// 로또
		int []lotto = new int[6];
		
		for(int i = 0; i < lotto.length; i++) {			
			lotto[i] = (int)(Math.random() * 45 + 1); // 1 ~ 46
			// 중복 걸러내기
			for(int j = 0; j < i; j++) {
				if(lotto[i] == lotto[j]) { // 중복된 경우
//					continue;
					i--;
					break;
				} // if
//				System.out.println("중복 X");
			} // inner-for
		} // outter-for
		
		for(int value : lotto) {
			
			System.out.println(value);
		} // enhanced for
		
//		------------------------------
		
		// 2차원 배열
		int [][]arrTwo3; // 선언
		arrTwo3 = new int[5][];
		
//		arrTwo3[0] = new int[1];
//		arrTwo3[1] = new int[2];
//		arrTwo3[2] = new int[3];
//		arrTwo3[3] = new int[4];
//		arrTwo3[4] = new int[5];
		
		rowLength = arrTwo3.length;
		
		for(int i = 0; i < rowLength; i++) {
			arrTwo3[i] = new int[i+1];
		} // for
		
////		arrTwo3[0][0] = 1;
//		arrTwo3[0][ arrTwo3[0].length-1 ] = 1;
//		
//		arrTwo3[1][0] = 1;
//		arrTwo3[1][ arrTwo3[1].length-1 ] = 1;
//		
//		arrTwo3[2][0] = 1;
//		arrTwo3[2][ arrTwo3[2].length-1 ] = 1;
//		
//		arrTwo3[3][0] = 1;
//		arrTwo3[3][ arrTwo3[3].length-1 ] = 1;
//		
//		arrTwo3[4][0] = 1;
//		arrTwo3[4][4] = 1;
//		arrTwo3[4][ arrTwo3[4].length-1 ] = 1;
		
		for(int i = 0; i < rowLength; i++ ) {
			arrTwo3[i][0] = 1;
			
			int colLength = arrTwo3[i].length; // 열
			
			for(int j=1; j<colLength-1; j++) {
				arrTwo3[i][j] = arrTwo3[i][j] 
						= arrTwo3[i-1][j-1] + arrTwo3[i-1][j];
			} // inner-for
			
			arrTwo3[i][colLength-1] = 1;
		} // outter-for
		
		for(int[]valueArr : arrTwo3) {
			
			for(int value : valueArr) {
				System.out.print(value + ",");
			} // inner-enhacned for
			
			System.out.println();
			
		} // outter-enhacned for
		
//		------------------------------
		
		// 점수
		
//		int[][] arrTwo4 = new int[10][3]; // 최대 10명의 학생 점수(국어, 수학, 영어)
		
//		arrTwo4[0][0] = 10; // 1번 학생의 국어점수는 10
//		arrTwo4[0][1] = 5; // 1번 학생의 수학점수는 5
//		arrTwo4[0][2] = 7; // 1번 학생의 영어점수는 7

		String []subject = {"국어", "수학", "영어"}; // 과목
		
		int subjectLength = subject.length;
		int [][]arrTwo4 = new int[10][subjectLength]; // 최대 10명의 학생 점수 (국어, 수학, 영어)
		int no = 0; // 학번
		
		// 점수 배열에 입력하기
		while(no < 10) {
			
			// 학생수(no)가 11인 경우에는 반복을 빠져나오기
			System.out.println("점수입력을 진행하시겠습니까 [y/n]");
			
			String yn = sc.next();
			
			if(yn.equals("n")) {
				break;
			} else if(yn.equals("y")) {
				
				for (int i = 0; i < subjectLength; i++ ) {
					System.out.println((no+1) + "번 학생의 " + subject[i] + "점수: ");
					arrTwo4[no][i] = sc.nextInt();
					
					totalScore = 0; // 학생별 총점
					
					for(int j = 0; j < subjectLength; j++) {
						totalScore += arrTwo4[i][j];
					} // inner-for
					
				} // outter-for
				
				// ↕️ 동일한 코드!
				
				/*
				System.out.print((no+1) + "학생의 국어점수");
				
				int k = sc.nextInt();
				arrTwo4[no][0] = k;
				
				System.out.print((no+1) + "학생의 수학점수");
				
				int m = sc.nextInt();
				arrTwo4[no][1] = m;
				
				System.out.print((no+1) + "학생의 영어점수");
				
				int e = sc.nextInt();
				arrTwo4[no][2] = e;
				*/
				
				no++;
				
			} else {
				System.out.println("잘못 입력하셨습니다.");
			} // if-else
			
		} // while
		
//		System.out.println(Arrays.deepToString(arrTwo4));
		
		// 학생들의 점수 출력하기 -------
		// ex)
		// 1번 학생 점수: 국어-, 수학-, 영어-
		// 2번 학생 점수: 국어-, 수학-, 영어-
		

		// 난 이게 최선인데 -ㅇ-..! -----
		// 배열에 입력된 점수 출력하기
//		for(int[] temp : arrTwo4) {
//            for(int j : temp) {
//                System.out.println(j);
//            } // inner-enhanced for
//            System.out.println("");
//        } // outter-enhanced for
		
		int []totalScoreSubject = new int[subjectLength]; // 과목별 총점
		
		System.out.println("학생들의 점수를 출력합니다.");
		
		for(int i = 0; i < no; i++) {
			System.out.println((i+1) + "번 학생의 점수입니다.");
			
			totalScore = 0; // 학생별 총점
			
			for(int j = 0; j < subjectLength; j++) {
				totalScore += arrTwo4[i][j];
			} // inner-for
			
			for(int j = 0; j < subjectLength; j++) {
				System.out.print(subject[j] + ": ");
				System.out.println(arrTwo4[i][j]);
			} // inner-for
			
			System.out.println("총점: " + totalScore);
			System.out.println("평균: " + (float)totalScore/3);
			
			// 과목별 총점 누적하기
			for(int j = 0; j<subjectLength; j++) {
				totalScoreSubject[j] += arrTwo4[i][j];
			} // inner-for
			
		 } // outter-for
		
		for(int i = 0; i < subjectLength; i++) {
			System.out.println("과목 평균: " + (float)totalScoreSubject[i]);
		} // for
		
	} // main
} // end class
