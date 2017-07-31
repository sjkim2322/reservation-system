package kr.or.seongjin.reservation.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.seongjin.reservation.dto.ReservationUser;

@Controller
public class MainController {
	
    @GetMapping(path = "/")
    public String index(){
        return "mainpage";
    }
    
    @GetMapping("/productDetail/*")
	public String detailProduct() {
		//model.addAttribute("productId",productId);
		return "detail";
	}
    
    @GetMapping(path = "/fileManage")
    public String file(){
        return "files";
    }
    
    @GetMapping("/reservation/*")
    public String reservation(){
        return "reserve";
    }
    
	@GetMapping("/myreservation")
    public String myreservation(){
        return "myreservation";
    }
	
	@GetMapping("/session/user")
	@ResponseBody
	public ReservationUser getUserFromSession(HttpSession session) {
		return  (ReservationUser) session.getAttribute("user");
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