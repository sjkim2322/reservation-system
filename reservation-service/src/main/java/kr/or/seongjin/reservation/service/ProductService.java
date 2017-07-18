package kr.or.seongjin.reservation.service;

import java.util.List;

import kr.or.seongjin.reservation.domain.Product;


public interface ProductService {

	public List<Product> selectAllByCategory(int categoryId,int offset);
	public int countByCategory(int categoryId);
}
