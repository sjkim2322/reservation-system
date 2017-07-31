package kr.or.seongjin.reservation.service;

import java.util.List;

import kr.or.seongjin.reservation.dto.UserComment;

public interface UserCommentService {

	public List<UserComment> listUserCommentByProductId(Integer productId,Integer limit,Integer page);
	public String getAvgScore(Integer productId);
	public Integer getTotalCount(Integer productId);
	public UserComment insertComment(UserComment userComment,List<Integer> fileList);
	
}
