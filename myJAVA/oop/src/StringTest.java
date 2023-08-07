import java.util.StringTokenizer;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class StringTest {

	public static void main(String[] args) {
		
		String s1 = "HELLO";
		String s2 = "안녕하세요";
		
		// 0번 index의 값
		char c1 = s1.charAt(0); // 'H'
		char c2 = s2.charAt(0); // '안'
		
		// 문자열에서는 length() 사용! -> 문자열의 길이 획득 가능
		int size1 = s1.length(); // 5
		int size2 = s2.length(); // 5
		
		log.info("s1.charAt(0) : " + c1);
		log.info("s2.charAt(0) : " + c2);
		
		log.info("s1.length() : " + size1);
		log.info("s2.length() : " + size2);
		
		log.info("******************************************");
		
//		--------------------------------
		
		// Palindrome Example
		
		String s3 = "가나다라다나가";
		
		// 길이만큼 배열 생성
		char[] Palindrome = new char[s3.length()];
		
		// 배열 입력
		for(int i = 0; i < Palindrome.length; i++) {
			char temp = s3.charAt(i); // s3.charAt(i) = str이라는 문자열에 i번째 인덱스의 문자를 뽑아서 temp 변수에 할당함
			
			Palindrome[i] = temp; 	// -> 배열에 값을 부여함
		} // for
		
		// 문자열이 Palindrome인지 아닌지 비교를 위한 flag라는 boolean 타입 변수 선언
		boolean flag = true;
		
		// 비교 시작
		for(int i = 0; i < Palindrome.length; i++) {
			// 0번째 index와 끝의 index부터 비교 시작
			if(Palindrome[i] == Palindrome[Palindrome.length - 1 - i]) { // 일치
				System.out.println(Palindrome[i] + " == " + Palindrome[Palindrome.length - 1 - i]);
	            
				flag = true;
	         } else { // 불일치
	            System.out.println(Palindrome[i] + " != " + Palindrome[Palindrome.length - 1 - i]); // 값이 동일하지 않을 경우 출력문
	            
	            flag = false;
	            break;
	         } // if-else
		} // for
		
		// 결과 출력
		if(flag == true) {
			log.info("Palindrome 문자열입니다!");	
		} else {
			log.info("Palindrome 문자열이 아닙니다!");
		} // if-else
		
		log.info("******************************************");
		
//		--------------------------------
		
		// 강사님
		String s4 = "스위스";
		int size = s4.length(); // 5
		int i = 0;
		
		// 문자열의 절반까지만 반복
		for(;i < size/2; i++) {
			
			if(s4.charAt(i) != s4.charAt(size-1-i)) {
				break;
			} // if
			
		} // for
		
		// 반복 끝까지
		if(i == size/2) {
			log.info("Palindrome 문자열입니다!");
		} else {
			log.info("Palindrome 문자열이 아닙니다!");
		} // if-else
		
		log.info("******************************************");
		
//		--------------------------------
		
		// .split()
		
//		String s5 = "100:70:65";
		String s5 = "100::65"; // NumberFormatException
		// Integer.parseInt(str); = "100", "", "65"로 반환되기 때문!
		
		// 구분자 :
		String delim = ":";
	
		String[] arr = s5.split(delim); // 결과 배열로 반환
		
		for(String str : arr) {
			if(str.equals("")) {
				log.info("미응시");
			} else {
//			log.info("arr: " + str);
				log.info("Integer.parseInt(str): " + Integer.parseInt(str));				
			} // if-else
		} // enhanced-for
		
		log.info("******************************************");
		
		// StringTokenizer
		
		// 빈 문자열 -> Token으로 분리 X (빈 문자열과 같이 Token이 아닌 것들은 분리하지 않음)
		StringTokenizer st = new StringTokenizer(s5, delim);
		
		// 분리할 Token이 있는지
		while(st.hasMoreTokens()) {
			String str = st.nextToken(); // "100", "65"
			
			log.info("StringTokenizer의 결과: " + str);
		} // while
		
	} // end main
	
} // end class
