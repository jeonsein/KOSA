public class Operator {
    public static void main(String[] args) {

        // 산술연산자
        int a, b, c;
        
        a = 2;
        b = 3;
        c = a + b; // 산술 연산 후 대입 연산됨! 연산자 우선 순위가 있음

        System.out.println(c); // 5

        System.out.println(c+"1"); // int + String 5+"1" => "51"
        System.out.println(c+1+"2"); // int + int + String 5+1+"2" => "62"
        System.out.println("2"+c+1); // String + int + int
        System.out.println("2"+(c+1)); // => "26"

        System.out.println(a/b); // 정수 나누기 정수의 결과는 정수 0
        // System.out.println(a/0); // 정수를 0으로 나눈 결과는 ArithmeticException 예외 발생
        System.out.println(c%a); // 나머지값 1

        float f1, f2;
        f1 = 2F;
        f2 = 0F;

        System.out.println( f1/f2 ); // 실수를 0으로 나눈 결과는 Infinity

        // 대입연산자
        a = 2;
        a += 2; // a = a+2와 같음
        System.out.println(a); // 4
        a *= 3; // a = a * 3;
        System.out.println(a); // 12

        // 단항연산자
        a = 2;
        a++; // a=a+1; 또는 a+=1;와 같음
        System.out.println(a); // 3

        a = 2;
        b = a++; // b = a; 이후 a+1 
        System.out.println(a); // 3
        System.out.println(b); // 2 

        a = 2;
        b = ++a; // ++a 이후 b=a;
        System.out.println(a); // 3
        System.out.println(b); // 3

        // byte
        byte by = 127;
        by++;
        System.out.println(by); // -128 
        // 최대 범위인 127 넘어가면 -128부터 재시작

        // 비교 연산자
        System.out.println(1>0); // true;
        System.out.println(1<0); // false;
        System.out.println(a%2==1); // true;
        System.out.println(a%2!=0); // true;

        // 논리 연산자
        System.out.println(1>0 && a%2==1); // true
        System.out.println(1>0 && a%2!=1); // false
        System.out.println(1<0 && a%2==1); // false
        System.out.println(1<0 && a%2!=1); // false

        System.out.println(1>0 & a%2!=1); // false
        System.out.println(1<0 & a%2==1); // false
        System.out.println(1<0 & a%2!=1); // false

        System.out.println(1>0 || a%2==1); // true
        System.out.println(1>0 || a%2!=1); // true
        System.out.println(1<0 || a%2==1); // true
        System.out.println(1<0 || a%2!=1); // false

        System.out.println(1&2); // 비트연산자
        System.out.println(true&false); // 논리연산자

        System.out.println( !(a%2==1) ); // false

        // 삼항연산자
        System.out.println( a%2==1? "홀수" : "짝수"); // 홀수
    }
}