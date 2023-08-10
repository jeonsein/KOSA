package com.my.tcp.server;

import java.io.IOException;
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
		
		// ServerSocket 타입의 객체 생성
		try {
			ss = new ServerSocket(port);
			System.out.println("Client의 접속을 기다리기!");
			
			// 클라이언트 접속 시, 서버에서는 자동으로 Socket 생성!
			s = ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		
	} // end main
	
} // end class
