package kr.or.seongjin.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.seongjin.reservation.dao.UserDao;
import kr.or.seongjin.reservation.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int getUserIdBySnsId(int snsId) throws Exception {
		try{
			return userDao.selectUserIdBySnsId(snsId);
		} catch (Exception e) {
			//exception
			throw new Exception();
		}
	}
}
