package com.my.tcp.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


class ReceiveForClientMultiThread extends Thread{
	
	private Socket s;
	private InputStream is = null;
	private DataInputStream dis =null;
	
	public ReceiveForClientMultiThread(Socket s) throws IOException {
		this.s = s;
		is = s.getInputStream();
		dis = new DataInputStream(is);
	} // constructor
	
	@Override
	public void run() {
		try {
			String receiveMsg;
			
			while(  !(receiveMsg = dis.readUTF()).equals("quit")) {
				System.out.println("클라이언트가 보낸 메시지:" + receiveMsg);
			} // while
			
		} catch (IOException e) {
		} finally {
			System.out.println("클라이언트와의 연결이 종료되었습니다");
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {} // try-catch
			} // if
		} // try-catch-finally
		
	} // run()
	
} // end class

//---------------------------------------------------

class SendForClientMultiThread extends Thread{
	
	private Socket s;
	private OutputStream os = null;
	private DataOutputStream dos = null;
	
	SendForClientMultiThread(Socket s) throws IOException {
		this.s = s;
		os = s.getOutputStream();
		dos = new DataOutputStream(os);
	} // constructor
	
	@Override
	public void run() {
		try {
			for(int i=0; i<100; i++) {
				dos.writeUTF("fromServerMsg-" + i);
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) { e.printStackTrace(); } // try-catch
			} // for
		} catch (IOException e) { e.printStackTrace(); } // try-catch
	
	} // run()
	
} // end class

//---------------------------------------------------

public class ServerForClientMultiThread {
	
	public static void main(String[] args) {
		
		int port = 5432;
		ServerSocket ss = null;
		Socket s = null;
		
		
		try {
			ss = new ServerSocket(port);
			
			while(true) {
				try {
					System.out.println("클라이언트 접속을 기다리기");
					
					s = ss.accept();
					
					//수신용스레드
					new ReceiveForClientMultiThread(s).start();
					//송신용스레드
					new SendForClientMultiThread(s).start();
				} catch(EOFException e) {
				} catch (IOException e) { e.printStackTrace(); }  // try-catch
			}// while
			
		} catch(BindException e) {
			System.out.println(port +"포트가 이미 사용중입니다");
		} catch (IOException e1) { e1.printStackTrace(); } // try-catch

	} // end main

} // end class