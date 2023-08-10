package com.my.tcp.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {

	public static void main(String[] args) {

		Socket s = null;
//		String serverIP = "127.0.0.1";	// network가 연결되어 있지 않아도 사용 가능한 IP
//										// = localhost
//		String serverIP = "192.168.260.260.1"; // UnknownException
		
		// 셍나 학원 컴퓨터 IP 주소 192.168.1.21
		String serverIP = "192.168.1.22"; // 찬돌이 IP : 192.168.1.22
		
		int serverPort = 5432;
		
		OutputStream oos = null;
		DataOutputStream dos = null;
		
		try {
			s = new Socket(serverIP, serverPort);
			System.out.println("▷▷▷▷▷ 서버와의 연결 성공 ;-) ");
			
			// 문자 보내기
			oos = s.getOutputStream();
			oos.write(65);
			
			// 문자열 보내기
//			dos = new DataOutputStream(oos);
//			dos.writeUTF("안녕하세야 셍나입니다 ㅇ_<");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("▷▷▷▷▷ " + serverIP + " 서버가 존재하지 않습니다 -ㅇ-!");
			System.out.print("▷▷▷▷▷ 서버 IP를 확인하세요!");
		} catch (ConnectException e) {
			e.printStackTrace();
			System.out.println("▷▷▷▷▷ 서버가 실행되지 않았습니다 -ㅇ-!\n▷▷▷▷▷ 서버 실행을 확인하세요!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {} // try-catch
			} // if
		} // try-catch-finally
		
	} // end main

} // end class
