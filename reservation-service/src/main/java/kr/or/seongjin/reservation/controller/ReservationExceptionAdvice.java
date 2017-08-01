package kr.or.seongjin.reservation.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.seongjin.reservation.Exception.NotExistProductException;
import kr.or.seongjin.reservation.Exception.ReservationException;

@ControllerAdvice
public class ReservationExceptionAdvice {

	@ExceptionHandler(NotExistProductException.class)
	@ResponseBody
	public String HandleExcetion(NotExistProductException e){
		System.out.println(e.getMessage() + " " + e.getExceptionCode());

		return "/";
	}

}
