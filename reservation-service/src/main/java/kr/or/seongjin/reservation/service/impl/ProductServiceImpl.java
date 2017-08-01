package kr.or.seongjin.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.seongjin.reservation.Exception.NotExistProductException;
import kr.or.seongjin.reservation.dao.ProductDao;
import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.ProductPrice;
import kr.or.seongjin.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	@Autowired
	void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}


	@Override
	public List<Product> selectAllByCategoryForMainPage(int categoryId, int offset) {
		if (categoryId == 0)
			return ListFilter(productDao.selectAll(offset));
		else
			return ListFilter(productDao.selectByCategory(categoryId, offset));
	}

	@Override
	public int countByCategory(int categoryId) {
		if (categoryId == 0)
			return productDao.countAll();
		else
			return productDao.countByCategory(categoryId);
	}

	@Override
	public Product getDetailProduct(Integer productId) throws NotExistProductException {
		Product product = productDao.selectByProductId(productId);
		if(product == null){
			System.out.println("@@");
			throw new NotExistProductException();
		} else {
			return product;
		}
	}

	@Override
	public List<String> getImagesByProductId(Integer productId) {

		return productDao.selectImagesByProductId(productId);
	}

	@Override
	public List<ProductPrice> getPricesByProductId(Integer productId) {
		return productDao.selectPricesByProductId(productId);
	}
			
	private void addPlaceNameToList(Product product){
			product.setPlace_name(productDao.selectPlaceNameByProductId(product.getId()));
	}
	
	private void addRepresentImg(Product product) {
			String imgId;
			imgId=productDao.selectReprentImgByProductId(product.getId());
			
			if(imgId==null) {
				product.setImgPath("0");
			}
			else {
			product.setImgPath(imgId);
			}
	}
	private List<Product> ListFilter(List<Product> productList) {
		for(Product product : productList) {
			addPlaceNameToList(product);
			addRepresentImg(product);
		}
		return productList;
	}


	@Override
	public String getProductName(Integer productId) {
		return productDao.selectProductName(productId);
	}
}
