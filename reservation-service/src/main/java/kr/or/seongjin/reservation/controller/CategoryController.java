package kr.or.seongjin.reservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.seongjin.reservation.domain.Category;
import kr.or.seongjin.reservation.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	CategoryService categoryService;


	@GetMapping(path = "/categories_rest")
	public String categories_rest(Model model) {

		return "categories_rest";
	}

	@GetMapping(path = "/categories")
	public String categories(Model model) {

		model.addAttribute("categoryList", categoryService.selectAll());
		return "categories";
	}

	@PostMapping(path = "/categories/insert")
	public String categoriesInsert(HttpServletRequest req) {

		Category category = new Category((String) req.getParameter("name"));
		categoryService.insert(category);
		return "redirect:/task/categories";
	}

	@PostMapping(path = "/categories/update")
	public String categoriesUpdate(@ModelAttribute Category category) {
		categoryService.update(category);
		return "redirect:/task/categores";
	}

	@PostMapping(path = "/categories/delete")
	public String categoriesDelete(@ModelAttribute Category category) {

		categoryService.delete(category.getId());
		return "redirect:/task/categories";
		
	}
}
