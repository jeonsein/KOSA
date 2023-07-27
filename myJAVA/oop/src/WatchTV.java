// 제공자 소스
/**
 * TV용 클래스
 * @author sengna :-)
 * @version 1.0
 */
class TV{

	boolean power;
	int channel;
	int volume;
	
	/**
	 * 전원을 켠다
	 */
	void powerOn() {
		power = true;
	};
	
	/**
	 * 전원상태를 반환한다
	 * @return 전원이 켜진 상태면 ture 반환, 꺼진 상태면 false를 반환한다.
	 */
	boolean isPower() {
		return power;
	}
	
	/**
	 * 채널을 설정한다
	 * @param i 설정할 채널값
	 */
	void setChannel(int i) {
		channel = i;
	}
	
	/**
	 * 현재 채널값을 반환한다.
	 * @return 현재 채널값
	 */
	int getChannel() {
		return channel;
	}
	
	/**
	 * 채널값을 1 증가한다
	 */
	void channelUp() {
		channel++;
	}
	
} // TV

// 사용자 소스
public class WatchTV {

	public static void main(String[] args) {
		
		TV tv;			// 참조형 지역변수 선언
		tv = new TV(); 	// 인스턴스화
		
		System.out.println(tv.power); // false
		System.out.println(tv.channel); // 0
		System.out.println(tv.volume); // 0
		
		tv.powerOn();
		boolean flag = tv.isPower();
		
		if(flag) {
			System.out.println("전원이 켜졌습니다.");
			
			tv.setChannel(11); // 채널 설정
			
			int ch = tv.getChannel(); // 현재 채널을 반환
			System.out.println("현재 채널은 " + ch); // 11
			
			tv.channelUp(); // 채널을 1 증가한다.
			System.out.println("현재 채널은 " + tv.getChannel()); // 12
		} else {
			System.out.println("전원이 꺼졌습니다.");
		} // if-else
		
		TV tv1 = new TV();
		tv1.powerOn();
		
	} // main

} // end class
