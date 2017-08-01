package kr.or.seongjin.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session!=null && session.getAttribute("user")!=null) {
			return super.preHandle(request, response, handler);
		}
		else {
			System.out.println("emptySession");
			response.sendRedirect("/login?originPath="+createOriginPath(request));
			//(requestURI, queryString)URL encode
			return false;
		}
	}
	
	private String createOriginPath(HttpServletRequest request) {
		String originPath = request.getRequestURI();
		String queryString = request.getQueryString();
		if(queryString != null) {
			originPath = originPath + "?" + queryString;
		}
		
		try {
			System.out.println(URLEncoder.encode(originPath,"UTF-8"));
			return URLEncoder.encode(originPath,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "/";
		}
	}

}
