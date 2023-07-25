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
                System.out.println(i);
            }
            System.out.println(i);
            i++;
        }
        System.out.println( );
    }

}