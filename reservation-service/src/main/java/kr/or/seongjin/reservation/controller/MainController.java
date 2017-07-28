package kr.or.seongjin.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.seongjin.reservation.dto.User;

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
	public User getUserFromSession(HttpSession session) {
		return  (User) session.getAttribute("user");
	}

	@GetMapping("/review")
    public String review(){
        return "reviewWrite";
    }
  
    
}
