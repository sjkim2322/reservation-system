package kr.or.seongjin.reservation.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import kr.or.seongjin.reservation.dto.NaverUser;
import kr.or.seongjin.reservation.dto.User;

public interface LoginService {

	public String requestCertification(HttpSession session,String originPath) throws UnsupportedEncodingException;
	public User requestUserInfo(String code, String state);
	public User LogIn(User user);
}
