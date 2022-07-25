package com.nandotech.boaleituralivraria.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nandotech.boaleituralivraria.entity.User;
import com.nandotech.boaleituralivraria.repository.UserRepository;
import com.nandotech.boaleituralivraria.service.CookieService;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String index(Model model) {

		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, User loginData, HttpServletResponse response, HttpSession session) {

		User user = userRepository.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());

		if (user == null) {
			model.addAttribute("error", "Usuário ou senha inválidos");
			return "login";
		}
		
		/* Cookie & Session management */
		CookieService.setCookie(response, "user", user.getEmail(), 3600);
		CookieService.setCookie(response, "userAdmin", String.valueOf(user.isAdmin()), 3600);
		
		session.setAttribute("user", user.getEmail());
		session.setAttribute("userAdmin", String.valueOf(user.isAdmin()));
		session.setMaxInactiveInterval(3600);

		return "redirect:";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpServletResponse response, HttpSession session) {
		
		/* Cookie & Session management */
		CookieService.setCookie(response, "user", null, 0);
		CookieService.setCookie(response, "userAdmin", null, 0);
		
		session.setAttribute("user", null);
		session.setAttribute("userAdmin", null);
		session.invalidate();
		
		return "redirect:";
	}
}
