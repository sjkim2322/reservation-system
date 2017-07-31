package kr.or.seongjin.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.Reservation;
import kr.or.seongjin.reservation.domain.ReservationCount;
import kr.or.seongjin.reservation.domain.ReservationDTO;
import kr.or.seongjin.reservation.dto.User;
import kr.or.seongjin.reservation.service.ReservationService;
import kr.or.seongjin.reservation.service.UserService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	
	private ReservationService reservationService;
	private UserService userService;
	
	@Autowired
	void setProductService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@Autowired
	void setProductService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
	public Product getReservation(@PathVariable Integer id) {
		return null;
	}

	@GetMapping
	public List<ReservationDTO> getReservationByUserId(HttpSession session) {
		User user = (User)session.getAttribute("user");
		try {
			return reservationService.getReservationByUser(userService.getUserIdBySnsId(user.getId()));
		} catch (Exception e) {
			// TODO exception
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/count")
	public List<ReservationCount> getReservationCountByUserId(HttpSession session) throws Exception {	
		User user = (User)session.getAttribute("user");
		return reservationService.getReservationCountByUser(userService.getUserIdBySnsId(user.getId()));
	}
	
	@PostMapping
	public void setReservation(HttpSession session, @RequestBody Reservation reservation) throws Exception {
		User user = (User)session.getAttribute("user");
		reservation.setUserId(userService.getUserIdBySnsId(user.getId()));
		reservationService.setReservation(reservation);
	}
	
	@PutMapping("/{id}")
	public void modifyReservationTypeById(@PathVariable int id, @RequestBody int type) {
		reservationService.modifyReservationTypeById(id, type);
	}
}
