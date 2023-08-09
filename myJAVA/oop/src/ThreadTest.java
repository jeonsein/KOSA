class Sound extends Thread {
	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(" sound" + i);
		} //for
	} // run()
} // end class

class Caption implements Runnable {
	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			System.out.print(Thread.currentThread().getName());
			System.out.println(" caption" + i);
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
		
//		s.run(); // 스레드로서 시작하는 것이 아님!
		s.start();
		
//		-----------------------------
		
		/*
		// 재사용성 높은 스레드일 경우 클래스 이름을 만든다!
		Caption c = new Caption();
//		c.start(); // 상속 관계 X라서 안됨!
//		🔽🔽 이렇게 해줘야 함!
		Thread t = new Thread(c);
		t.start();
		*/
		
		/*
		// 재사용성 없는 스레드인 경우 클래스 이름을 만들지 않는다!
		// = 익명 클래스로 객체 생성!
		Thread t = new Thread( new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 3; i++) {
					System.out.print(Thread.currentThread().getName());
					System.out.println(" caption" + i);
				} //for
			} // run()
		});
		t.start();
		*/
//		🔽🔽🔽🔽
		
		// 람다식으로 변경
		// ()-> 이 부분이 아래 코드와 동일!
//		new Runnable() {
//		@Override
//		public void run()
		
		Thread t = new Thread(()-> {
			for(int i = 0; i < 3; i++) {
				System.out.print(Thread.currentThread().getName());
				System.out.println(" caption" + i);
			} //for
		});
		t.start();
		
//		-----------------------------
		
		for(int i = 0; i < 3; i++) {
			System.out.println(" video" + i);
		} // for
		
		System.out.println(" ☆☆☆☆☆☆ THE END ☆☆☆☆☆☆ ");
		
	} // end main
	
} // end class
