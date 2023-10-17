package com.my.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;


@WebFilter("/*")
public class MyFilter extends HttpFilter implements Filter {
       

    public MyFilter() {
        super();
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("MyFilter.doFilter() 호출됨.");
		
		chain.doFilter(request, response); // 다음 필터에 연결시킴
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
