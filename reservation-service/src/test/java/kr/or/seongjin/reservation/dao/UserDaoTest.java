package kr.or.seongjin.reservation.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.seongjin.config.RootApplicationContextConfig;
import kr.or.seongjin.reservation.domain.Category;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class UserDaoTest {

	@Autowired
	private UserDao dao;


 
	@Test
	public void shouldSelect() {
		// given
		System.out.println((dao.selectUser(36364979)));
	}
	
		
	
	
	
	

}
