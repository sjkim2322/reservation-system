package kr.or.seongjin.reservation.service;

import java.util.List;

import kr.or.seongjin.reservation.dto.UserComment;

public interface UserCommentService {

	public List<UserComment> listUserCommentByProductId(Integer productId);
	public String getAvgScore(Integer productId);
	public Integer getTotalCount(Integer productId);
	
}
