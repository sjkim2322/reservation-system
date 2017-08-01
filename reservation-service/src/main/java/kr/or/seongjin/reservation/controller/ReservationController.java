package kr.or.seongjin.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
	
    @GetMapping("/reservation/*")
    public String reservation(){
        return "reserve";
    }
    
	@GetMapping("/myreservation")
    public String myreservation(){
        return "myreservation";
    }
	
}