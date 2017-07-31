package kr.or.seongjin.reservation.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReservationExceptionAdvice {

	@ExceptionHandler
	public String HandleExcetion(){
		return "/errorpage";
	}
}
