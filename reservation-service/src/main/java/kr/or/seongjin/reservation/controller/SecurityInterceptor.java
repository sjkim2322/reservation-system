package kr.or.seongjin.reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Intercepted!! path :" + request.getServletPath());
		HttpSession session = request.getSession();
		if(session!=null && session.getAttribute("user")!=null) {
			return super.preHandle(request, response, handler);
		}
		else {
			System.out.println("emptySession");
			response.sendRedirect("/login?originPath="+request.getServletPath());
			return false;
		}
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	
	

}
