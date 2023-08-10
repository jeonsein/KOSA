package com.my.tcp.client;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class test extends Thread {
	
	
	
} // end class

public class ClientMultiThread {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		// Socket 생성
		Socket s = null;
		ServerSocket ss = null;

		s = new Socket();
		ss = new ServerSocket();
		
		OutputStream os = null;
		DataOutputStream dos = null;
		
		// 스레드 
		Thread t = new Thread() {
			// 작업 스레드
			@Override
			public void run() {
				
			}
		}
		
	} // end main
	
} // end class
