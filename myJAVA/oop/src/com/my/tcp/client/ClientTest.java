package com.my.tcp.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	public static void main(String[] args) {

		Socket s = null;
		String serverIP = "127.0.0.1";	// network가 연결되어 있지 않아도 사용 가능한 IP
										// = localhost
		int serverPort = 5432;
		
		OutputStream oos = null;
		
		try {
			s = new Socket(serverIP, serverPort);
			System.out.println("연결 성공!");
			
			oos = s.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		
		// 학원 컴퓨터 IP 주소 192.168.1.21
		
	} // end main

} // end class
