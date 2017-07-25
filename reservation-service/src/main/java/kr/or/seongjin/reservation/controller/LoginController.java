package kr.or.seongjin.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import kr.or.seongjin.reservation.dao.UserDao;
import kr.or.seongjin.reservation.dto.NaverLoginToken;
import kr.or.seongjin.reservation.dto.NaverToken;
import kr.or.seongjin.reservation.dto.User;
import kr.or.seongjin.reservation.service.LoginService;

@Controller
public class LoginController {

	
	
	
	private LoginService loginService;
	
	@Autowired
	public void setLoginServie(LoginService loginService) {
		this.loginService= loginService;
	}
	
	@GetMapping(path = "/login")
	public String login(HttpSession session,
			@RequestParam(value="originPath",required=false)String originPath) throws UnsupportedEncodingException{
		System.out.println(originPath);
		String apiURL= loginService.requestCertification(session,originPath);
      return "redirect:"+apiURL;
	}

	@GetMapping(path = "/login/callback")
	  public String callback(@RequestParam(value="code",required=false)String code,
			  @RequestParam(value="state")String state,
			  @RequestParam(value="originPath")String originPath,
			  HttpSession session) {
		
		  if(session.getAttribute("state").equals(state)) {
			  User user = loginService.requestUserInfo(code,state);
			  session.setAttribute("user", loginService.logIn(user));
				
			  return "redirect:"+originPath;
		  }
		  else {
			  return "redirect:/";
		  }
	  }
}
