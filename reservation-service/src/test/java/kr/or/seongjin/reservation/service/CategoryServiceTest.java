package kr.or.seongjin.reservation.service;

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
public class CategoryServiceTest {

	private CategoryService setterInjection;
	
	private CategoryService constructorInjection;
	
	@Autowired
	private CategoryService fieldInjection;
	
	@Autowired
	void setFieldInjection(CategoryService fieldInjection) {
		if(this.fieldInjection==fieldInjection)
		this.fieldInjection=fieldInjection;
	}
	
	
	
	
	@Test
	public void fieldTest() {
		System.out.println("field"+fieldInjection);
	}
	
	
}
