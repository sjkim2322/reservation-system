package kr.or.seongjin.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.ReservationCount;
import kr.or.seongjin.reservation.domain.ReservationDTO;
import kr.or.seongjin.reservation.service.ProductService;
import kr.or.seongjin.reservation.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	
	private ProductService productService;
	private ReservationService reservationService;

	@Autowired
	void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	void setProductService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	
	@GetMapping("/{reservationId}")
	public Product getReservation(@PathVariable Integer productId) {
		return null;
	}

	@GetMapping
	public List<ReservationDTO> getReservationByUserId(HttpSession session) {
		try {
			return reservationService.getReservationByUser((int) session.getAttribute("id"));
		} catch (Exception e) {
			// TODO exception
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/count")
	public List<ReservationCount> getReservationCountByUserId(HttpSession session){
		
		try {
			return reservationService.getReservationCountByUser((int) session.getAttribute("id"));
		} catch (Exception e) {
			//TODO exception
			e.printStackTrace();
		}
		return null;
	}
	
}
