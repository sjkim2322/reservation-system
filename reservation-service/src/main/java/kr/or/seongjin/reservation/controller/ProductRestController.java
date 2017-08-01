package kr.or.seongjin.reservation.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.ProductPrice;
import kr.or.seongjin.reservation.dto.UserComment;
import kr.or.seongjin.reservation.service.ProductService;
import kr.or.seongjin.reservation.service.UserCommentService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

	private ProductService productService;
	private UserCommentService userCommentService;

	@Autowired
	public void setUserCommentService(UserCommentService userCommentService) {
		this.userCommentService = userCommentService;
	}

	@Autowired
	void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{productId}")
	public Product getDetailProduct(@PathVariable Integer productId) throws Exception {

		return productService.getDetailProduct(productId);
	}

	@GetMapping(path = "/{productId}/productName", produces = "application/text; charset=utf8")
	public String getProductName(@PathVariable Integer productId, HttpServletResponse response) throws Exception {
		return productService.getProductName(productId);
	}

	@GetMapping("/{productId}/images")
	public List<String> getImages(@PathVariable Integer productId) {
		return productService.getImagesByProductId(productId);
	}

	@GetMapping("/{productId}/prices")
	public List<ProductPrice> getPrices(@PathVariable Integer productId) {
		return productService.getPricesByProductId(productId);
	}

	@GetMapping("/{productId}/comments")
	public List<UserComment> getListCommentByProductId(@PathVariable Integer productId, HttpServletResponse response,
			@RequestParam int page, @RequestParam int limit) {

		response.addHeader("avgScore", userCommentService.getAvgScore(productId));
		response.addIntHeader("totalCount", userCommentService.getTotalCount(productId));
		return userCommentService.listUserCommentByProductId(productId, limit, page);
	}

}
