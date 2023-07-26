package basic;

public class Condition{
    public static void main(String[] args) {
        
        /*
        // int a = 4;
        // Math.random();  // 0.0 이상, 1.0 미만
        // int a = (int)Math.randome(); // 0이상 1미만
        int a = (int)(Math.random() * 100 ); // 0이상 100미만

       System.out.println(a);

        if( a%2 == 1 ) {
            System.out.println("홀수");
            System.out.println("제곱값은" + (a * a)); 
        }   
    
        if(a%2 == 1) {
            System.out.println("홀수");
        } else{
            System.out.println("짝수");
        }
        */
       
        /*
        //int year = 2023;
        System.out.print("출생 년도를 입력하세요.");
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int year = sc.nextInt();
        
        System.out.println(year + "년도에 해당하는 동물을 출력하시오.");
        // 년도를 12로 나눈 나머지값이 0이면 원숭이,

        int mod = year % 12;
        if ( mod == 0) {
            System.out.println("원숭이");
        } else if ( mod == 1 ) {
            System.out.println("닭");
        } else if ( mod == 2 ) {
            System.out.println("개");
        }  else if ( mod == 3 ) {
            System.out.println("돼지");
        }  else if ( mod == 4 ) {
            System.out.println("쥐");
        }  else if ( mod == 5 ) {
            System.out.println("소");
        }  else if ( mod == 6 ) {
            System.out.println("호랑이");
        }  else if ( mod == 7 ) {
            System.out.println("토끼");
        }  else if ( mod == 8 ) {
            System.out.println("용");
        }  else if ( mod == 9 ) {
            System.out.println("뱀");
        }  else if ( mod == 10 ) {
            System.out.println("말");
        }  else if ( mod == 11 ) {
            System.out.println("양");
        };
        */

       /*
        // 평균 계산
        int koScore = 70;
        int engScore = 60;
        int mathScore = 84;
        int totalScore = koScore + engScore + mathScore; //총점
        float avg = (float)totalScore / 3; //평균
        System.out.println(avg);

        if( avg >= 90 ) {
            System.out.println("A등급 입니다.");
        } else if ( avg >= 80 ) {
            System.out.println("B등급 입니다.");
        } else if ( avg >= 70 ) {
            System.out.println("C등급 입니다.");
        } else if ( avg >= 60 ) {
            System.out.println("D등급 입니다.");
        } else {
            System.out.println("F등급 입니다.");
        }

        // java.util.Scanner sc = new java.util.Scanner(System.in);
        // int score = sc.nextInt();

        // System.out.println("평균값을 입력하세요");
        */

        /*
        System.out.println("가위바위보 게임 입니다. ");
        System.out.println("가위-1, 바위-2, 보-3을 입력하세요.");

        java.util.Scanner sc = new java.util.Scanner(System.in);
        int u = sc.nextInt();
        int r = (int)(Math.random() * 3 +1);
        System.out.println("컴퓨터가 낸 값은" + r);

        if ( u == r ) {
            System.out.println("비김");
        } else if ( u < 1 || u > 3 ) {
            System.out.println("1~3의 숫자를 입력하세요.");
        // } else if( (u == 1 && r == 2) || (u == 2 && r == 3) || (u == 3 && r == 1 ) ) {
        } else if ( u - ( r % 3 ) == 1 ) {
            System.out.println("이김");
        } else {
            System.out.println("짐");
        } 

        System.out.println("");
       */

        int a = 4;

        switch( a % 2 ) {
            case 1:  // a 를 2로 나눈값이 1
                System.out.println("홀수");
                break;
            case 0:  // a 를 2로 나눈 값이 0 
                System.out.println("짝수");
        }

        int year = 2023;
        int mod = year % 12;
        switch(mod) {
        
        case 0:
	   System.out.println("원숭이띠");
	   break;
        case 1:
	   System.out.println("닭띠");
	   break;
        default:
	   System.out.println("그외의 동물");
        }

        java.util.Calendar c;
        c = java.util.Calendar.getInstance(); // 현재 날짜 객체
        int month = c.get(java.util.Calendar.MONTH); // 주의: 1월인 경우 0을 반환, 2월인 경우 1을 반환함
        System.out.println(month); // 7

        if( month <= 5 ) {
            System.out.println("현재" + ( month + 1 ) + "월은 상반기 입니다.");
        } else {
            System.out.println("현재" + ( month + 1 ) + "월은 하반기 입니다.");
        }

        switch(month) {
        
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
	   System.out.println("현재" + ( month + 1 ) + "월은 상반기 입니다.");
	   break;
        default:
	   System.out.println("현재" + ( month + 1 ) + "월은 하반기 입니다.");
        }
        

    }

}