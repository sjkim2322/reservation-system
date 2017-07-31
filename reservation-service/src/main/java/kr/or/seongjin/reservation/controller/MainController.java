package kr.or.seongjin.reservation.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.seongjin.reservation.dto.User;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class.getName());
	
    @GetMapping(path = "/")
    public String index(){
    	logger.info("log : mainpage!!!!!");
        return "mainpage";
    }
    
    @GetMapping("/productDetail/*")
	public String detailProduct() {
    	logger.info("log : detail!!!!");
		//model.addAttribute("productId",productId);
		return "detail";
	}
    
    @GetMapping(path = "/fileManage")
    public String file(){
    	logger.info("log : file Manage!!!!!");
        return "files";
    }
    
    @GetMapping("/reservation/*")
    public String reservation(){
    	logger.info("log : reservation!!!!!");
        return "reserve";
    }
    
	@GetMapping("/myreservation")
    public String myreservation(){
		logger.info("log : my!!@!@!@!!!!!!");
        return "myreservation";
    }
	@GetMapping("/session/user")
	@ResponseBody
	public User getUserFromSession(HttpSession session) {
		return  (User) session.getAttribute("user");
	}


  
    
}
