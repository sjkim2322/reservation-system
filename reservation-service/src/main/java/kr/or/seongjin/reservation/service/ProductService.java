package kr.or.seongjin.reservation.service;

import java.util.List;

import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.ProductPrice;


public interface ProductService {

	public List<Product> selectAllByCategoryForMainPage(int categoryId,int offset);
	public int countByCategory(int categoryId);
	public Product getDetailProduct(Integer productId);
	public List<String> getImagesByProductId(Integer productId);
	public List<ProductPrice> getPricesByProductId(Integer productId);
}