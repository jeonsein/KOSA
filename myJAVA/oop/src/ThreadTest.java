class Sound extends Thread {
	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(" sound" + i);
		} //for
	} // run()
} // end class

public class ThreadTest {
	
	public static void main(String[] args) {
		
//		Thread ct = Thread.currentThread();
//		ct.getName();
		// ↕️ 위아래 동일 코드
//		Thread.currentThread().getName();
		
		System.out.println
		("Thread.currentThread().getName()의 결과는: " 
				+ Thread.currentThread().getName());
		
		Sound s = new Sound();
		s.run(); // 스레드로서 시작하는 것이 아님!
//		s.start();
		
		System.out.println("☆☆☆☆☆☆ THE END ☆☆☆☆☆☆");
		
//		System.out.println("잠실 토박이는 맛집 같은거 몰라");
//		System.out.println("타지역 애들이 더 잘안다구");
//		System.out.println("잠실은 다 체인점이라,,,, 그게 그거라구,,,");
		
	} // end main
	
} // end class
