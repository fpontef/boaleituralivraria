package com.nandotech.boaleituralivraria.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nandotech.boaleituralivraria.entity.User;
import com.nandotech.boaleituralivraria.repository.UserRepository;
import com.nandotech.boaleituralivraria.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/admin")
	public String indexAdmin(Model model) {

		List<User> userList = userService.list();
		model.addAttribute("userList", userList);

		return "admin-user-index";
	}

	/* Página do Form */
	@GetMapping("/admin/create")
	public String createAdmin(User user) {

		return "admin-user-new";
	}

	/* Página do Form */
	@GetMapping("/create")
	public String create(User user) {

		return "user-register";
	}

	/* Método do Form - Admin */
	@PostMapping("/admin")
	public String storeAdmin(Model model, User user) {

		userService.save(user);

		return "redirect:/user/admin";
	}

	/* Método do Form - User */
	@PostMapping("")
	public String store(Model model, User user, HttpServletRequest request) {

		/* My worst and unsecure way to block user to self admin, sorry */
		user.setAdmin(false);

		userService.save(user);

		return "redirect:";
	}

	@GetMapping("/profile")
	public String showProfile(Model model, HttpSession session) {
		
		var user = userRepository.findByEmail(String.valueOf(session.getAttribute("user")));

		model.addAttribute("userDetails", user);
		
		return "user-profile";
	}
	
	/* Página do Form */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {

		var user = userService.find(id);
		model.addAttribute("user", user);

		return "user-edit";
	}

	/* Método do Form - Admin */
	@PostMapping("/{id}")
	public String updateAdmin(@PathVariable("id") Long id, Model model, User user) {

		userService.update(user);

		return "redirect:/user/admin";
	}
	
	/* Método do Form - User*/
	@PostMapping("/admin/{id}")
	public String update(@PathVariable("id") Long id, Model model, User user) {
		
		user.setAdmin(false);
		
		userService.update(user);
		
		return "redirect:/user/admin";
	}

	@DeleteMapping("/admin/{id}")
	public String destroy(@PathVariable Long id, Model model) {
		userService.delete(id);

		return "redirect:/user/admin";
	}

}
