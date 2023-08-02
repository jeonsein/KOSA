import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * GUI 프로그램 순서
 * 1. 이벤트 소스(bt)와 이벤트의 종류(Action Event) 결정
 * 2. 이벤트 처리용 클래스(Event Handler)를 작성
 * class MyHandler implements ActionListener {
 * }
 * 3. 이벤트 소스와 이벤트 핸들러를 연결하기!
 * 		bt.addActionListener( new MyHandler() );
 */

//----------------------------------

// ver_1
//class MyHandler implements ActionListener {
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		System.out.println("클릭되었습니다 :-)");
//	} // actionPerformed()
//	
//} // end class

// ver_2 - outter class : 비추
//class MyHandler implements ActionListener {
//	
//	// 초기값 참조형 변수이기 때문에 초기값 null 
//	// -> 실행하면 NPE 터짐
////	private JTextField t;
//	
//	// GUITest Class에서 사용되는 t를 사용해야 함
//	private JTextField t;
//	MyHandler(JTextField t) {
//		this.t = t;
//	} // -> 생성자를 통해서 GUITest에서 사용되는 객체(t)가 
//	// MyHandler 클래스로 전달되고, 해당 클래스에서 사용 가능해짐
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		t.setText("클릭되었습니다 :-) ");
//	} // actionPerformed()
//	
//} // end class

//	----------------------------------

public class GUITest {
	private JFrame f;
	private JButton bt;
	private JTextField t;
	
// ver_3 - inner class
//	class MyHandler implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			t.setText("클릭되었습니다 :-) ");
//		} // actionPerformed()
//	
//	} // end class	
	
	public GUITest() {
		
		f = new JFrame("프레임");			// 창 (프레임)
		bt = new JButton("클릭");			// 버튼
		t = new JTextField("입력하세요");	// 한 줄 입력창
		
		Container c = f.getContentPane();	// 프레임 뒷 판
		
		c.setLayout(new FlowLayout());		// 순서대로 배치
		
		c.add(bt);
		c.add(t);
		
		// 이벤트 소스와 이벤트 핸들러 연결
		// ver_1
//		bt.addActionListener( new MyHandler() );
		
		// ver_2
//		bt.addActionListener( new MyHandler(t) );
		
		// ver_3
//		bt.addActionListener( new MyHandler() );
		
		// ver_4 - 익명구현객체
//		bt.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				t.setText("클릭되었습니다 :-) ");
//			} // actionPerformed()	
//		}); // 이름없는 클래스 타입의 객체 생성 (익명클래스 형태로 객체 생성)
		
		// ver_5 - Lambda
		bt.addActionListener( (e) -> {
			t.setText("클릭되었습니다 :-) ");
		});
		
		
		f.setSize(300, 200);
		f.setVisible(true);
		
	} // GUITest()
	
	public static void main(String[] args) {
		
		new GUITest();
	
	} // end main

} // end class