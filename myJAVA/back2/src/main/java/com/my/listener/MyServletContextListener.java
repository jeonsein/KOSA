package com.my.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    	
    } // MyServletContextListener(0

    public void contextDestroyed(ServletContextEvent sce)  { 
    	// 프로젝트가 끝날 때 정리하기 위한 작업들을 여기 기술!
    } // contextDestroyed()

    // 생성 시 감시하는 메소드
    public void contextInitialized(ServletContextEvent sce)  { 
    	// 프로젝트 시작 전, 준비 작업을 위한 코드를 여기 기술!
    	// ex) DB와의 작업을 위한 ConnectionPool을 만들기 
    	// -> DB와의 딜레이 문제 해결이 가능함
    	
    	System.out.println("MyServletContextListener.contextInitialized() 호출됨");
    	
    } // contextInitialized()
	
} // end class
