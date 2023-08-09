//class Share extends Thread {
class Share {
	
	private int i;
	
	public void push() {
		
		for(int i = 0; i < 10; i++) {
			
			// i 변수 값 보존을 위한 동기화 처리
			// lock 부분을 짧게짧게 쓰는 것을 권장!
			synchronized(this) {	// 잠금 장치
				this.notify(); // 깨워주기
							   // 이 공유 객체를 사용하는 wait된 스레드를 깨움!
				
				System.out.println("Before push: i = " + this.i);
				this.i++;
				System.out.println(", After push: i = " + this.i);
			} // synchronized ()
			
		} // for
		
		// 🔼🔼🔼🔼🔼
		
		/* for 100번 돌리는 동안 보존!
		synchronized(this) {
			for(int i = 0; i < 100; i++) {
				System.out.println("Before push: i = " + this.i);
				this.i++;
				System.out.println(", After push: i = " + this.i);
			} // for
		} // synchronized ()
		*/
		
	} // push()
	
	public void pop() {
		for(int i = 0; i < 10; i++) {
			
			synchronized(this) {	// 잠금 장치
				if(this.i == 0) {
					try {
						this.wait(); // 재우기
									 // 이 공유 객체를 사용하는 
									 // 현재 스레드를 일시 중지함!
					} catch (InterruptedException e) {
						e.printStackTrace();
					} // try-catch
				} // if
				
				System.out.println("Before pop: i = " + this.i);
				this.i--;
				System.out.println(", After pop: i = " + this.i);
			} // synchronized ()
			
		} // for		
	} // pop()

} // end class

//--------------------------------------------------

class Push extends Thread {
	private Share s;
	
	Push(Share s) {
		this.s = s;
	} // push constructor
	
	@Override
	public void run() {
		this.s.push();
	} // run()

} // end class

//--------------------------------------------------

class Pop extends Thread {
	private Share s;
	
	Pop(Share s) {
		this.s = s;
	} // Pop constructor
	
	@Override
	public void run() {
		this.s.pop();
	} // run()

} // end class

// --------------------------------------------------

public class ThreadShareTest {

	public static void main(String[] args) {

		Share s;
		s  = new Share();
		
		Push push = new Push(s);
//		push.s = s; // 접근 범위가 없다면 이렇게 하면 댐!
		
		Pop pop = new Pop(s);
		
		// 3개의 Thread가 CPU 점유하려고 함
		push.start();
		pop.start();
		
	} // end main

} // end class
