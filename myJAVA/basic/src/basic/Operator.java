package basic;

public class Operator{
    public static void main(String[] args) {
        // 산술연산자
        int a, b, c;
        a = 2;
        b = 3;
        c = a + b; // 산술연산후 대입연산됨, 연산자 우선순위가 있음
        System.out.println(c); // 5

        System.out.println( c + "1" );
        System.out.println( c + + 1 + "2" );
        System.out.println( "2" + c + 1);
        System.out.println( "2" + (c + 1));



        System.out.println(a / b); // 정수 나누기 정수  결과는 정수 0 이다.
        // System.out.println(a / 0);  // 정수를 0으로 나눈 결과는 ArithmeticException 예외 발생
        System.out.println(c % a); // 나머지값 

        float f1, f2;
        f1 = 2;
        f2 = 0;
        System.out.println(f1 / f2); // 실수를 0으로 나눈 결과는 Infinity


       // 대입연산자
       a = 2;
       a += 2; // a = a+2;와 같음
       System.out.println(a); // 4
       a *= 3; 
       System.out.println( a );

       // 단항 연산자
       a = 2;
       a++;
       System.out.println(a);

       a = 2;
       b = a++; // b = a; a++; // b에 a값 대입하고 a를 1 증가하라 
       System.out.println(a); // 3
       System.out.println(b); // 2 

       a = 2;
       b = ++a; // ++a; b=a; // ++를 먼저 하고 b에 대입 하니까 3이 출력된다.
       System.out.println(a); // 3
       System.out.println(b); // 3 

       byte by = 127;
       by++;
       System.out.println(by); // -128


       // 비교 연산자
       System.out.println( 1 > 0 ); // true
       System.out.println( 1 < 0 ); // false
       System.out.println( a%2 == 1 ); // true
       System.out.println( a%2 != 0 ); // true
 
       // 논리 연산자
       System.out.println( 1 > 0 && a % 2 == 1); // true  연산자 두개일 때 왼쪽이 true이면 오른쪽도 연산함
       System.out.println( 1 > 0 && a % 2 != 1 ); // false  연산자 두개일 때 왼쪽이 false면 오른쪽은 연산하지 않음
       System.out.println( 1 < 0 && a % 2 == 1 ); // false
       System.out.println( 1 < 0 && a % 2 != 1 ); // false

       System.out.println( 1 > 0 & a % 2 != 1 ); // false 연산자 한개만 썼을 때 왼쪽이 true이면 오른쪽도 연산함
       System.out.println( 1 < 0 & a % 2 == 1 ); // false 연산자 한개만 썼을 때 왼쪽이 false이더라도 오른쪽도 연산함
       System.out.println( 1 < 0 & a % 2 != 1 ); // false


       System.out.println( 1 > 0 || a % 2 == 1); // true
       System.out.println( 1 > 0 || a % 2 != 1 ); // true
       System.out.println( 1 < 0 || a % 2 == 1 ); // true
       System.out.println( 1 < 0 || a % 2 != 1 ); // false

       System.out.println( 1 & 2 ); // 비트 연산자
       System.out.println( true & false );  // 논리 연산자

       System.out.println( !(a%2 == 1) ); // false 

       // 삼항 연산자
       System.out.println( a%2 == 1 ? "홀수" : "짝수" );

       // 연산 우선순위
       System.out.println( 1+2*3/4 ); // 2*3 -> (2*3)/4, 1+((2*3)/4) ==> 2
       System.out.println( (1+2)/3*4 ); // ==> 4


    }

}