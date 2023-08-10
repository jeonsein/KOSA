package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTest {

	public static void main(String[] args) {

		// 사용 할 포트번호 
		// (0 ~ 1024까지는 예약 포트이기 때문에 제외하고 사용하기)
		int port = 5432;
		
		// 예외처리를 위해 지역변수에 null값 해당
		ServerSocket ss = null;
		Socket s = null;
		
		InputStream is = null;
		OutputStream oos = null;
		
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		// ServerSocket 타입의 객체 생성
		try {
			ss = new ServerSocket(port);
			System.out.println("Client의 접속을 기다리기!");
			
			// 클라이언트 접속 시, 서버에서는 자동으로 Socket 생성!
			s = ss.accept();
			
			is = s.getInputStream();
			
			// #1 고정된 문자 하나 출력!
//			is = s.getInputStream();
//			int readValue = is.read();
//			System.out.println("▷▷▷▷▷ Client가 보낸 메세지: " + (char)readValue);
			
			// #2 고정된 문자열 출력!
//			dis = new DataInputStream(is);
//			String readValue = dis.readUTF();
//			System.out.println("▷▷▷▷▷ Client가 보낸 메세지: " + readValue);
			
			// #3 chandollbabo 입력 전까지, 문자열 출력
//			dis = new DataInputStream(is);
//			String receiveMsg;
//			while( !(receiveMsg = dis.readUTF()).equals("chandollbabo") ) {
//				System.out.println("▷▷▷▷▷ Client가 보낸 메세지: " + receiveMsg);
//			} // while
			
			// #4 Server가 되돌려준 메시지
			dis = new DataInputStream(is);
			oos = s.getOutputStream();
			dos = new DataOutputStream(oos);
			String receiveMsg;
			while( !(receiveMsg = dis.readUTF()).equals("chandollbabo") ) {
				System.out.println("▷▷▷▷▷ Client가 보낸 메시지: " + receiveMsg);
				dos.writeUTF(receiveMsg);
			} // while
//			int readValue = is.read();
//			System.out.println("▷▷▷▷▷ Client가 보낸 메시지: " + (char)readValue);
			
		} catch (BindException e) {
			e.printStackTrace();
			System.out.println("※※※※※ " + port + " 포트가 이미 사용 중입니다 -ㅇ- !");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("※※※※※ 클라이언트와의 연결이 종료되었습니다. :-( ");
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {} // try-catch
			} // if
		} // try-catch-finally
		
	} // end main
	
} // end class
