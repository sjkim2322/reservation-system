package kr.or.seongjin.reservation.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.seongjin.config.RootApplicationContextConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ProductDaoTest {

	private ProductDao productDao;

	@Autowired
	void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

//	@Test
//	public void shoudSelectAll() {
//		assertThat(productDao.selectAll().size(),is(12));
//	}

}
