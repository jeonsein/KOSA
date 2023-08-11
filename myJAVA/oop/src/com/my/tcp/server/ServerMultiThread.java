package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


// 데이터 수신용 Thread
class ServerThread extends Thread {
	
	private Socket s;
	private InputStream is = null;
	private DataInputStream dis = null;
	private OutputStream os = null;
	private DataOutputStream dos = null;
	
	private List<ServerThread> list;
//	private ArrayList<ServerThread> list;
//	private Vector<ServerThread> list;
	
	private String clientIP;
	
	ServerThread(Socket s, List<ServerThread> list) throws IOException {
		this.s = s;
		this.list = list;
		
		is = s.getInputStream();
		dis = new DataInputStream(is);
		os = s.getOutputStream();
		dos = new DataOutputStream(os);
		
		// IP에 대한 라이브러리
		// 소켓과 연결된 클라이언트 IP 정보를 얻어낼 수 있음
		InetAddress ip = s.getInetAddress();
		
		// IP 정보를 문자열로 얻어냄!
		clientIP = ip.getHostAddress();
		
		System.out.println("※※※※※ " + clientIP + "님이 접속했습니다.");
		
		broadcast("※※※※※ " + clientIP + "님이 접속했습니다.");
		
	} // constructor
	
	// 부모에서 선언한 예외만 (Override한)자식쪽에서 throws 할 수 있음!
	@Override
	public void run() {

		try {
			String receiveMsg;
			
			while( !(receiveMsg = dis.readUTF()).equals("quit")) {
				
//				System.out.println("클라이언트가 보낸 메시지: " + receiveMsg);
//				dos.writeUTF(receiveMsg);
				
				// 접속한 모든 클라이언트에게 송신되도록!
//				for(ServerThread t : list) {
//					t.dos.writeUTF(clientIP + " > " + receiveMsg); 
//				} // for
//				🔽🔽🔽🔽 대체
				broadcast(clientIP + " > " + receiveMsg);
				
			} // while
		} catch (SocketException e) {} 
		catch (EOFException e) {} 
		catch (IOException e) {}
		finally {
			list.remove(this);
			
			System.out.println("※※※※※ " + clientIP + "님과의 연결이 종료되었습니다. :-( ");
			
			broadcast(clientIP + "님이 나갔습니다.");
			
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {} // try-catch
			} // if
		} // try-catch-finally
		
	} // run()
	
	private void broadcast(String msg) {
		for(ServerThread t : list) {
			try {
				t.dos.writeUTF(msg);
			} catch (IOException e) {} // try-catch
		} // for
	} // broadcast()
	
} // end class

// ---------------------------------------------------

public class ServerMultiThread {
	
	// 메인 Thread
	public static void main(String[] args) throws IOException {
	
		// Socket 생성
		Socket s = null;
		ServerSocket ss = null;
		
		int serverPort = 5432;
		
		List<ServerThread> list = new ArrayList<>(); // 일반화된 인터페이스 타입
//		ArrayList<ServerThread> list = new ArrayList<>(); // 구체화된 클래스 타입
		// 처음에 구체화된 클래스 타입으로 선언했기 때문에 코드 변경이 일어났음 (ArrayList -> Vector)
		// 만약 처음부터 일반화된 인터페이스 타입으로 선언했다면,
		// main 내부에서 객체 생성하는 부분만 변경이 가능함!
		// List<ServerThread> list = new ArrayList<>();
		// 🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽🔽
		// List<ServerThread> list = new Vector<>(); 
		
//		Vector<ServerThread> list = new Vector<>();
		
		try {
			
			ss = new ServerSocket(serverPort);
			
			System.out.println("※※※※※ 클라이언트 접속을 기다리는 중입니다.");
			
			while(true){
				s = ss.accept();
				
				ServerThread t = new ServerThread(s, list);
				list.add(t);
				
				t.start();
			} // while
			
		} catch(BindException e) {
			System.out.println("※※※※※ " + serverPort +"포트가 이미 사용중입니다.");
		} catch (IOException e) {e.printStackTrace();} 
	
	} // end main
	
} // end class