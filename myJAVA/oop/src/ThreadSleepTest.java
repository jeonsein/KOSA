import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSleepTest {

	public static void main(String[] args) {

		System.out.println("현재 스레드의 이름: " + Thread.currentThread().getName());
		
		// 새로운 스레드 생성 (람다식)
		new Thread(()-> {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			
			for(int i = 0; i < 10; i++) {
				Date dt = new Date();
				
				// main의 지역변수를 사용 가능!
				System.out.println(sdf.format(dt));
				
				// 1초간 일시 중지
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
//					e.printStackTrace();
				} // try-catch
				
			} // for
			
		}).start();
		
		
	} // end main

} // end class
