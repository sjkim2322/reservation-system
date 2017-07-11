package kr.or.seongjin.reservation.service;

import java.util.List;

import kr.or.seongjin.reservation.domain.Category;

public interface CategoryService {

	public Category insert(Category category);
	public Category selectById(int id);
	public List<Category> selectAll();
	public boolean update(Category category);
	public boolean delete(int id);
}
