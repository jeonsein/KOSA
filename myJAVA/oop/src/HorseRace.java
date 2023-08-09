import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Log4j2
class Horse extends Canvas implements Runnable {
	private String name;
	private int x;
	private int y;
	
	Horse(String name){
		this.name = name;
		x = 50;
		y = 10;
	} // constructor
	
	@Override
	public void paint(Graphics g) {
		g.drawString(name, x, y);
	} // paint()

	@Override
	public void run() {
		for(int i = 0; i < 20; i++) {
			System.out.println("말 이름: " + name);
			
			this.setX(this.getX()+10);
			this.repaint();
			
			// 말 쉬게 하기
			// 1.0 <=  <= 501.0
			long millis = (long)(Math.random()*500+1);
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // try-catch
		} // for
	} // run()
	
} // end class

public class HorseRace {
	private JFrame f; // frame
	private JButton btReady, btStart; // buttons
	private Horse[] horses;
	
	public HorseRace(){
		f = new JFrame("달려라 찬도리!!!");
		f.setLayout(new GridLayout(4,1));
		
		Container c = f.getContentPane(); // frame 뒷 판에 배치
		
		horses = new Horse[3];
		
		for(int i=0; i<horses.length; i++) {
			horses[i]= new Horse((i+1)+"번 찬돌_🏇🏻");
			c.add(horses[i]);	// 추가된 Horse의 paint()가 자동 호출!
		} // for
		
		Panel p = new Panel();
		
		btReady = new JButton("찬돌이 준비!");
		btStart = new JButton("찬돌이 달려!");
		
		p.add(btReady);p.add(btStart);
		c.add(p);
		
		btReady.addActionListener((e)-> {
			// 말 clear (준비 버튼 클릭 시, 출발선으로 돌아감)
			for(Horse h : horses) {
				h.setX(0);
				h.repaint(); // horse 객체의 drawing이 clear!
							 // 이후 paint() 호출됨
			} // for
		}); // lambda

		btStart.addActionListener((e)-> {
			// 말 clear (준비 버튼 클릭 시, 출발선으로 돌아감)
			for(Horse h : horses) {
				/*
				for(int i = 0; i < 20; i++) {
					h.setX(h.getX()+10); // 기존 x 위치에 + 10씩!
					// -> 반복을 제일 먼저 도는 말 = 1번 말이기 때문에
					// 항상 1번 말이 우승함 굳굳
					
					h.repaint(); // horse 객체의 drawing이 clear!
					// 이후 paint() 호출됨
				} // inner-for
				*/
				
				// 말들 다투게 하기!
				Thread t = new Thread(h);
				t.start();
				
			} // outter-for
		}); // lambda
		
		f.setSize(500, 200);
		f.setVisible(true);
		
	} // HorseRace()
	
	public static void main(String[] args) {
		new HorseRace();
	} // end main

} // end class
