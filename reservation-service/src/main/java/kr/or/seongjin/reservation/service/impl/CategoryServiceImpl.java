package kr.or.seongjin.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.seongjin.reservation.dao.CategoryDao;
import kr.or.seongjin.reservation.domain.Category;
import kr.or.seongjin.reservation.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao dao;
	
	
	@Override
	public Category insert(Category category) {
		int id;
		id = dao.insert(category);
		return dao.selectById(id);
	}

	@Override
	public Category selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public List<Category> selectAll() {
		return dao.selectAll();
	}

	@Override
	public boolean update(Category category) {
		if(dao.update(category)!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(int id) {
		if(dao.delete(id)!=0)
			return true;
		else
			return false;
	}

}
