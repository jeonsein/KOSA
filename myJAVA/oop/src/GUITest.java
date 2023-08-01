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

class MyHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("클릭되었습니다 :-)");
	} // actionPerformed()
	
}

public class GUITest {
	private JFrame f;
	private JButton bt;
	private JTextField t;
	
	public GUITest() {
		
		f = new JFrame("프레임");			// 창 (프레임)
		bt = new JButton("클릭");			// 버튼
		t = new JTextField("입력하세요");	// 한 줄 입력창
		
		Container c = f.getContentPane();	// 프레임 뒷 판
		
		c.setLayout(new FlowLayout());		// 순서대로 배치
		
		c.add(bt);
		c.add(t);
		
		// 이벤트 소스와 이벤트 핸들러 연결
		bt.addActionListener( new MyHandler() );
		
		f.setSize(300, 200);
		f.setVisible(true);
		
	} // GUITest()
	
	public static void main(String[] args) {
		
		new GUITest();
	
	} // end main

} // end class