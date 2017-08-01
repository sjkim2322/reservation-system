package kr.or.seongjin.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.dto.ReservationUser;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    
	@GetMapping
	public ReservationUser getUserFromSession(HttpSession session) {
		return  (ReservationUser) session.getAttribute("user");
	}
	
}
