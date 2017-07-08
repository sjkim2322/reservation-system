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
public class CategoryDaoTest {

	@Autowired
	private CategoryDao dao;


 
	@Test
	public void shouldInsertAndSelect() {
		// given
		Category category = new Category("test");

		// when
		Integer id = dao.insert(category);

		// then
		Category selected = dao.selectById(id);
		assertThat(selected.getName(), is("test"));
	}
	
	@Test
	public void shouldUpdate() {
		//given
		Category category = new Category("test");
		category.setId(dao.insert(category));
		category.setName("updated");
		//when
		int affected = dao.update(category);
				
		//then
		assertThat(affected,is(1));
		
	}
	
	@Test
	public void shouldDelete() {
		//given
		Category category = new Category("test");
		int testId = dao.insert(category);
		
		//when
		int affected = dao.delete(testId);
		
		//then
		assertThat(affected,is(1));
	}
	
	
	
	

}
