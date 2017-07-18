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
@RequestMapping("/task")
public class Task1Controller {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public String task(Model model) {

		return "index";
	}

	@GetMapping(path = "/task1_rest")
	public String task1_rest(Model model) {

		return "task1_rest";
	}

	@GetMapping(path = "/task1")
	public String task1(Model model) {

		model.addAttribute("categoryList", categoryService.selectAll());
		return "task1";
	}

	@PostMapping(path = "/task1/insert")
	public String task1Insert(HttpServletRequest req) {

		Category category = new Category((String) req.getParameter("name"));
		categoryService.insert(category);
		return "redirect:/task/task1";
	}

	@PostMapping(path = "/task1/update")
	public String task1Update(@ModelAttribute Category category) {
		categoryService.update(category);
		return "redirect:/task/task1";
	}

	@PostMapping(path = "/task1/delete")
	public String task1Delete(@ModelAttribute Category category) {

		categoryService.delete(category.getId());
		return "redirect:/task/task1";
	}
}
