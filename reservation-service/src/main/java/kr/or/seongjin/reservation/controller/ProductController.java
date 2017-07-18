package kr.or.seongjin.reservation.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	private ProductService productService;

	@Autowired
	void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{categoryId}")
	public List<Product> task1(@PathVariable int categoryId,HttpServletRequest request,HttpServletResponse  response) {
		response.addIntHeader("totalCount", productService.countByCategory(categoryId));
		return productService.selectAllByCategory(categoryId,Integer.parseInt(request.getHeader("offset")));
	}
}
