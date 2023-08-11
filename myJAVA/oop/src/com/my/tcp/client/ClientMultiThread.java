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


// 데이터 수신용 Thread
class ClientThread extends Thread {
	
	private Socket s;
	InputStream is = null;
	DataInputStream dis = null;
	
	ClientThread(Socket s) throws IOException {
		this.s = s;
		
		is = s.getInputStream();
		dis = new DataInputStream(is);
	} // constructor
	
	// 목적: 무한반복 돌면서 수신받기
	@Override
	public void run() {
		
		// #1 Eclipse가 추천해준 try-catch 코드
		// 소켓은 한번 망가졌으면 사용 불가능한데,
//		while(true) {
//			String receiveMsg;
//			try {
//				receiveMsg = dis.readUTF();
//				System.out.println("서버가 되돌려준 메시지: " + receiveMsg);
//			} catch (IOException e) {
//				e.printStackTrace();
//			} // try-catch
//		}// while
		
		// 🔽🔽🔽🔽🔽🔽 직접 수정하기

		// #2 직접 수정한 코드
		try {
			while(true) {
				String receiveMsg;
				receiveMsg = dis.readUTF();
				
				System.out.println("서버가 되돌려준 메시지: " + receiveMsg);
			} // while
		} catch (SocketException e) {
		} catch (EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} // try-catch
		
	} // run()
	
} // end class

// ---------------------------------------------------

public class ClientMultiThread {
	
	// 메인 Thread
	public static void main(String[] args) throws IOException {
	
		// Socket 생성
		Socket s = null;
		String serverIP = "127.0.0.1";
		int serverPort = 5432;
		
		OutputStream os = null;
		DataOutputStream dos = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			s = new Socket(serverIP, serverPort);
			System.out.println("서버와 연결 성공");
			
			new ClientThread(s).start(); //새로운 스레드 시작
			
			os = s.getOutputStream();
			dos = new DataOutputStream(os);

			String sendMsg;
			
			do {
				System.out.print("서버로 보낼 메시지(종료하려면 quit을 입력하세요.)");
				
				sendMsg = sc.nextLine();
				dos.writeUTF(sendMsg);
			} while(!sendMsg.equals("quit")); // do-while
			
		} catch (UnknownHostException e) {
			System.out.println(serverIP +"서버는 존재하지 않습니다. 서버IP를 확인하세요 ");
		} catch(ConnectException e) { //자식예외
			System.out.println("서버가 실행되지 않았습니다. 서버실행을 확인하세요");
		} catch(SocketException e) { //부모예외
			System.out.println("서버가 강제종료되었습니다. 서버확인하세요");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//dos.close(); -> oos.close() -> s.close();
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {} // try-catch
			} // if
		} // try-catch-finally
	
	} // end main
	
} // end class