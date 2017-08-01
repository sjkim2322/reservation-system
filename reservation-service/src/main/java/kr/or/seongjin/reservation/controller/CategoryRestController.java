package kr.or.seongjin.reservation.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.seongjin.reservation.domain.Category;
import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.service.CategoryService;
import kr.or.seongjin.reservation.service.ProductService;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
	
	
	private CategoryService categoryService;
	private ProductService productService;

	@Autowired
	void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	 
	
	 @GetMapping
	 Collection<Category> task1(Model model){
	        return categoryService.selectAll();
	 }
	    
	 @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
	 Category task1Insert(@RequestBody Category category){
	        return categoryService.insert(category);
	    }
	    
	 @PutMapping
	 boolean task1Update(@RequestBody Category category){
	        return categoryService.update(category);
	 }
	    
	 @DeleteMapping("/{id}")
	 boolean task1Delete(@PathVariable Integer id){
	        return categoryService.delete(id);
	 }
	
	@GetMapping("/{categoryId}/products")
	public List<Product> task1(@PathVariable Integer categoryId,HttpServletRequest request,HttpServletResponse  response) {
		response.addIntHeader("totalCount", productService.countByCategory(categoryId));
		return productService.selectAllByCategoryForMainPage(categoryId,Integer.parseInt(request.getHeader("offset")));
	}	 
}
