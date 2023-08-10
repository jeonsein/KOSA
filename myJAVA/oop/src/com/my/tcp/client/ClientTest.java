package com.my.tcp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClientTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		Socket s = null;
		String serverIP = "127.0.0.1";	// network가 연결되어 있지 않아도 사용 가능한 IP
//										// = localhost
//		String serverIP = "192.168.260.260.1"; // 존재 X IP - UnknownException
//		String serverIP = "192.168.1.21"; // 셍나 IP
//		String serverIP = "192.168.1.22"; // 찬돌이 IP
		
		int serverPort = 5432;
		
		InputStream is = null;
		OutputStream oos = null;
		
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			s = new Socket(serverIP, serverPort);
			System.out.println("▷▷▷▷▷ 서버와의 연결 성공 ;-) ");
			
			// #1 문자 보내기
			oos = s.getOutputStream();
//			oos.write(65);
			
			// #2 고정된 문자열 보내기
//			dos = new DataOutputStream(oos);
//			dos.writeUTF("안녕하세야 셍나입니다 ㅇ_<");
			
			// #3 chandollbabo 입력 전까지, 입력받은 문자열 보내기
//			dos = new DataOutputStream(oos);
//			String sendMsg;
			
			// #4 서버가 되돌려준 메시지
			dos = new DataOutputStream(oos);
			is = s.getInputStream();
			dis = new DataInputStream(is);
			String sendMsg;
			
			do {
				System.out.println("▷▷▷▷▷ 서버로 보낼 메시지를 입력하세요.");
				System.out.println("▷▷▷▷▷ 종료하려면, chandollbabo를 입력하세요 ㅇ_<");
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
				// #4 서버가 되돌려준 메시지
				String receiveMsg = dis.readUTF();
				System.out.println("▷▷▷▷▷ 서버가 되돌려준 메시지: " + receiveMsg);
			} while(!sendMsg.equals("chandollbabo"));
		} catch (EOFException e) {
//			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("※※※※※ " + serverIP + " 서버가 존재하지 않습니다 -ㅇ-!");
			System.out.print("※※※※※ 서버 IP를 확인하세요!");
		} catch (ConnectException e) { // 자식 예외
			e.printStackTrace();
			System.out.println("※※※※※ 서버가 실행되지 않았습니다 -ㅇ-!\n※※※※※ 서버 실행을 확인하세요!");
		} catch (SocketException e) {	// 부모 예외
			System.out.print("※※※※※ 서버가 강제로 종료되었습니다 -ㅇ-!\n※※※※※ 서버를 확인하세요!");
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
