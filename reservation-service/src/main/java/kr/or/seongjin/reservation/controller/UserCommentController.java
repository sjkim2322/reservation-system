package kr.or.seongjin.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.dto.UserComment;
import kr.or.seongjin.reservation.service.UserCommentService;

@RestController
@RequestMapping("/api/userComment")
public class UserCommentController {

	
	 private UserCommentService userCommentService;
	 
	 @Autowired
	 public void setUserCommentService(UserCommentService userCommentService) {
			this.userCommentService = userCommentService;
		}
	
	 @GetMapping("/{id}")
	 public List<UserComment> getListCommentByProductId(@PathVariable Integer id ,
			 HttpServletResponse  response,
			 @RequestParam int page,
			 @RequestParam int limit){
		 	
		 response.addHeader("avgScore", userCommentService.getAvgScore(id));
		 response.addIntHeader("totalCount", userCommentService.getTotalCount(id));
	        return userCommentService.listUserCommentByProductId(id,limit,page);
	 }
	    

//	@PostMapping
//    
//	
//	    
//	@PutMapping
//	
//	    
//	@DeleteMapping
//	
}
