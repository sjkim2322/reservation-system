package kr.or.seongjin.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.service.ProductService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	
	private ProductService productService;

	@Autowired
	void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/{reservationId}")
	public Product getReservation(@PathVariable int productId) {
		return null;
	}

	@GetMapping("/user")
	public Product getReservationByUserId(@PathVariable int productId) {
		return null;
	}
}
