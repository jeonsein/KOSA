package basic;


public class Loop {
    public static void main(String[] args) {
    
        int i  = 0;

        while( i < 5 ) {
            System.out.println("JAVA");
            i++;
        }

        int sum = 0;
        /*
        sum += 1;
        sum += 2;
        sum += 3;
        sum += 4;
        sum += 5;
        sum += 6;
        sum += 7;
        sum += 8;
        sum += 9;
        sum += 10;
        */

        i = 1;
        while( i <= 100 ) {
            sum += i;
            i++;
        }
        System.out.println(" 1~100 합 : " + sum);

        i = 1;
        int oddSum = 0;  // 홀수합
        int evenSum = 0; // 짝수합
        while ( i <= 100 ) {
            if( i % 2 == 1 ) {
                oddSum += i;
            } else {
                evenSum += i;
            }
            i++;
        }

        System.out.println("1~100의 홀수 합: " + oddSum + ", 짝수합:" + evenSum);

        System.out.println("1~100의 숫자를 출력하시오");
        i = 1;
        while ( i <= 100 ) {
            if ( i > 1 ) {
                System.out.print(",");
            }
            System.out.println(i);
            i++;
        }
        System.out.println( );
        
        // 값 치환
        
        int a, b;
        a = 10;
        b = 20;
        
        int temp;
        
        temp = a;
        a = b;
        b = temp;
        
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        
        char ch = 'A';
//        System.out.println(ch);
//        ch += 1;
//        System.out.println(ch);
//        ch ++;
//        System.out.println(ch);
        
        while( ch <= 'Z' ) {
        	System.out.print(ch + ", ");
        	ch++;  // ok
//        	ch += 1; // ok
//        	ch = ch+1; // error
//        	ch = (char)(ch+1); // ok
        }
        
        System.out.println();
        // 피보나치 수열
 
        int bbNum = 1; // 전전수
        int bNum = 0; // 전수
        int currentNum = bbNum+bNum; // 첫번째 현재수
        System.out.println(currentNum);
//        
//        // 전수를 전전수에 대입
//        // 현재수를 전수에 대입
//        
//        bbNum = bNum; // 0
//        bNum = currentNum; // 1
//        currentNum = bbNum + bNum; // 두번째 현재수 1
//        System.out.println(currentNum);
//        
//        // 전수를 전전수에 대입
//        // 현재수를 전수에 대입
//        
//        bbNum = bNum; // 1
//        bNum = currentNum;  // 1
//        currentNum = bbNum + bNum; // 2
//        System.out.println(currentNum);
//        
//        bbNum = bNum; // 1
//        bNum = currentNum;  // 2
//        currentNum = bbNum + bNum; // 3
//        System.out.println(currentNum);
        
        i = 0;
     
        while( i <= 20 ) {
        	
        	currentNum = bbNum + bNum; 
        	System.out.println("[i=" + i + "]" + currentNum + ",");
        	
            bbNum = bNum;
            bNum = currentNum; 
            i++;
        }
        
        
        System.out.println("1~10까지 출력하시오");
        i = 0;
        while ( i < 10 ) {
        	i++;
        	System.out.print(i + ", ");
        }
        
        System.out.println();
        
        // =====================  for문  =====================
        
        int f;
        int summ = 0;
        int oddSumm = 0;
        int evenSumm = 0;
        
        for(f = 1; f <= 100; f++) {
        	summ += f;
        }
        System.out.println("for문 1~100합: " + summ);
        
        System.out.println("1~100 까지 홀수의합, 짝수의 합 구하기");
        
        for(f = 1; f <= 100; f++) {
        	if( f % 2 == 1) {
        		oddSumm += f ;
        	} else {
        		evenSumm += f;
        	}
        }
        
        System.out.println("홀수의 합 :" + oddSumm + "짝수의 합 : " + evenSumm);
        
        // ========================================================
        
        for(i = 0; i <10;) {
        	i++;
        	System.out.println(i + ", ");
        }
        System.out.println();
      
        // do-while
  
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        String line;
        do {
        	System.out.println("메시지를 입력하세요, 작업을 중단하려면 exit를 입력하세요 : ");
        	line = sc.nextLine();
        	System.out.println("입력한 메시지 : " + line);
        }while(!line.equals("exit") );
        

        
    }

} // end class