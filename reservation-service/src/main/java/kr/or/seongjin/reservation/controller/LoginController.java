package kr.or.seongjin.reservation.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.seongjin.reservation.dto.NaverUser;
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
			@RequestParam(required=false)String originPath) throws UnsupportedEncodingException{
		System.out.println(originPath);
		String apiURL= loginService.requestCertification(session,originPath);
		System.out.println("api"+apiURL);
      return "redirect:"+apiURL;
	}

	@GetMapping(path = "/login/callback")
	  public String callback(@RequestParam(value="code",required=false)String code,
			  @RequestParam(value="state")String state,
			  @RequestParam(value="originPath")String originPath,
			  HttpSession session) {
		
		  if(session.getAttribute("state").equals(state)) {
			  NaverUser user = (NaverUser) loginService.requestUserInfo(code,state);
			  session.setAttribute("user", loginService.LogIn(user));
			  
			  System.out.println(originPath);
			  return "redirect:"+originPath;
		  }
		  else {
			  return "redirect:/";
		  }
	  }
}
