package kr.or.seongjin.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.seongjin.reservation.domain.ProductPrice;
import kr.or.seongjin.reservation.service.ProductService;

@Controller
@RequestMapping("/product/detail")
public class ProductDetailController {

	private ProductService productService;

	@Autowired
	void setProductService(ProductService productService) {
		this.productService = productService;
	}
	

	@GetMapping("/images/{productId}")
	@ResponseBody
	public List<String> getImages(@PathVariable Integer productId) {
		return productService.getImagesByProductId(productId);
	}
	
	@GetMapping("/prices/{productId}")
	@ResponseBody
	public List<ProductPrice> getPrices(@PathVariable Integer productId) {
		return productService.getPricesByProductId(productId);
	}
	
	
	
}
