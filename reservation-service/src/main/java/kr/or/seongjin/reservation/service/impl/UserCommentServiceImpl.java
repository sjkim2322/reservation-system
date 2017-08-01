package kr.or.seongjin.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.seongjin.reservation.dao.UserCommentDao;
import kr.or.seongjin.reservation.dto.UserComment;
import kr.or.seongjin.reservation.service.UserCommentService;

@Service
public class UserCommentServiceImpl implements UserCommentService {

	private UserCommentDao userCommentDao;
	
	@Autowired
	public void setUserCommentDao(UserCommentDao userCommentDao) {
		this.userCommentDao = userCommentDao;
	}
	
	private void addCommentImageToList(List<UserComment> commentList){
		for(UserComment userComment : commentList) {
			userComment.setImgList(userCommentDao.listImageByCommentId(userComment.getId()));
		}
	}
	private List<UserComment> ListFilter(List<UserComment> commentList) {
		
		addCommentImageToList(commentList);
		return commentList;
	}
	
	@Override
	public List<UserComment> listUserCommentByProductId(Integer productId,Integer limit,Integer page) {
		return ListFilter(userCommentDao.listByProductId(productId,limit,page));
	}

	@Override
	public String getAvgScore(Integer productId) {
		return userCommentDao.getAvgScore(productId);
	}

	@Override
	public Integer getTotalCount(Integer productId) {
		return userCommentDao.getTotalCount(productId);
	}

	@Transactional
	@Override
	public UserComment insertComment(UserComment userComment, List<Integer> fileList) {
		Integer insertedUserCommentId = userCommentDao.insertComment(userComment);
		for(Integer fileId :fileList) {
			userCommentDao.insertCommentImage(fileId,insertedUserCommentId);
		}
		return userCommentDao.selectById(insertedUserCommentId);
	}
	
	
	
}
