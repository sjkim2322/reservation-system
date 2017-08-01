package kr.or.seongjin.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

	@GetMapping("/*")
	public String detailProduct() {
		return "detail";
	}

	@GetMapping("/{id:[\\d]+}/reviews")
	public String comments() {
		return "review";
	}

	@GetMapping("/{id:[\\d]+}/review/register")
	public String review() {
		return "reviewWrite";
	}

}