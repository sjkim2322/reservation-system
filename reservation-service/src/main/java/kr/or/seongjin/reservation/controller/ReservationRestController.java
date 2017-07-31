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
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.Reservation;
import kr.or.seongjin.reservation.domain.ReservationCount;
import kr.or.seongjin.reservation.domain.ReservationDTO;
import kr.or.seongjin.reservation.dto.ReservationUser;
import kr.or.seongjin.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

	
	private ReservationService reservationService;
	
	@Autowired
	void setProductService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	

	@GetMapping("/{id}")
	public Product getReservation(@PathVariable Integer id) {
		return null;
	}

	@GetMapping
	public List<ReservationDTO> getReservationByUserId(HttpSession session) {
		ReservationUser user = (ReservationUser)session.getAttribute("user");
		try {
			return reservationService.getReservationByUser(user.getId());
		} catch (Exception e) {
			// TODO exception
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/count")
	public List<ReservationCount> getReservationCountByUserId(HttpSession session) {	
		ReservationUser user = (ReservationUser)session.getAttribute("user");
		try {
			return reservationService.getReservationCountByUser(user.getId());
		} catch (Exception e) {
			//TODO exception
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping
	public void serReservation(HttpSession session, @RequestBody Reservation reservation) throws Exception {
		ReservationUser user = (ReservationUser)session.getAttribute("user");
		reservation.setUserId(user.getId());
		reservationService.setReservation(reservation);
	}
	
	@PutMapping("/{id}")
	public void modifyReservationTypeById(@PathVariable int id, @RequestBody int type) {
		reservationService.modifyReservationTypeById(id, type);
	}
}
