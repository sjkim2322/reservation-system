package kr.or.seongjin.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.seongjin.reservation.dao.ProductDao;
import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	@Autowired
	void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> selectAllByCategory(int categoryId, int offset) {

		if (categoryId == 0)
			return productDao.selectAll(offset);
		else
			return productDao.selectByCategory(categoryId, offset);
	}

	@Override
	public int countByCategory(int categoryId) {
		if (categoryId == 0)
			return productDao.countAll();
		else
			return productDao.countByCategory(categoryId);
	}

}
