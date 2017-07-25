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
import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.ProductPrice;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootApplicationContextConfig.class)
@Transactional
public class ProductDaoTest {
	private ProductDao productDao;

	@Autowired
	void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Test
	public void shoudSelectAll() {
		
		System.out.println(productDao.selectPlaceNameByProductId(13));
	}
	@Test
	public void shouldPriceSelect() {
		for(ProductPrice productPrice : productDao.selectPricesByProductId(1)) {
			System.out.println(productPrice.toString());
		}
	}
	

}
