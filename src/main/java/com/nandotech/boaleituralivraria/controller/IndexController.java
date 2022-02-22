package com.nandotech.boaleituralivraria.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nandotech.boaleituralivraria.entity.Book;
import com.nandotech.boaleituralivraria.repository.BookRepository;

@Controller
public class IndexController {

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {

		List<Book> bookList = bookRepository.getNewArrival();
		model.addAttribute("bookList", bookList);

		return "index";
	}

}
