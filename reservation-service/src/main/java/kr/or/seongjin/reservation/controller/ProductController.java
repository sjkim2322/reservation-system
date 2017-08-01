package kr.or.seongjin.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ProductController {
	
    @GetMapping("/products/*")
	public String detailProduct() {
		//model.addAttribute("productId",productId);
		return "detail";
	} 
    
	@GetMapping("/products/{id:[\\d]+}/comments")
    public String comments(){
        return "review";
    }
	

	@GetMapping("/products/{id:[\\d]+}/review")
    public String review(){
        return "reviewWrite";
    }

}