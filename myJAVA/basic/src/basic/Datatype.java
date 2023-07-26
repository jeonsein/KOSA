package basic;


public class Datatype{
    public static void main(String[] args){
        byte b = 127;
        int i  = b;  // 자동형변환
        long l = 1234567890L;

        float f = 12.3F;
        double d = 12.3;

        f = i;  // 자동형변환
        f = l;  // 자동형변환

        b = (byte)d; // 강제 형변환 
        System.out.println(b);  // 12

        char c;
        c = '가';  // 유니코드 한글 '가'에 대한 10진수 값?
        i = c; // 자동 형변환  // 65
        System.out.println( i );

        // i = i + 1;
        i++;  // 66
        c = (char)i; // 4byte의 인트타입을 2바이트인 c로 대입하려면 강제형변환 필요 =>
        System.out.println( c );

    }

}