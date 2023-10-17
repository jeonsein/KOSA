package com.my.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class MySessionListener implements HttpSessionAttributeListener {
	private int loginedCnt;


    public MySessionListener() {
    	// 객체 생성, 생성자 호출될 때 출력됨
    	System.out.println("MySessionListener 객체 생성됨");
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	// 기존 attribute가 생성될 때 출력됨
    	System.out.println("MySessionListener.attributeAdded() 호출됨");
    	
    	if(se.getName().equals("loginedId")) {
    		loginedCnt++;
    		System.out.println("로그인 성공!");
    		System.out.println("로그인된 사용자 수: " + loginedCnt);
    	} // if
    } // attributeAdded()

    public void attributeRemoved(HttpSessionBindingEvent se)  {
    	// 기존 attribute가 제거될 때 출력됨
//    	System.out.println("MySessionListener.attributeRemoved() 호출됨");
    	
    	if(se.getName().equals("loginedId")) {
    		loginedCnt--;
    		System.out.println("로그아웃 성공!");
    		System.out.println("로그인된 사용자 수: " + loginedCnt);
    	} // if
    } // attributeRemoved()

    public void attributeReplaced(HttpSessionBindingEvent se)  {
    	
    }
	
} // end class
