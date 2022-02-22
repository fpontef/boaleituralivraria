package com.nandotech.boaleituralivraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nandotech.boaleituralivraria.entity.Book;
import com.nandotech.boaleituralivraria.repository.BookRepository;
import com.nandotech.boaleituralivraria.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("")
	public String index(Model model) {

		List<Book> bookList = bookService.list();
		model.addAttribute("bookList", bookList);

		return "book-index";
	}

	@GetMapping("/admin")
	public String indexAdmin(Model model) {

		List<Book> bookList = bookService.list();
		model.addAttribute("bookList", bookList);

		return "admin-book-index";
	}

	/* Página do Form */
	@GetMapping("/create")
	public String create(Book book) {

		return "admin-book-new";
	}

	/* Método do Form */
	@PostMapping("")
	public String store(Model model, Book book) {

		bookService.save(book);

		return "redirect:/book/admin";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {

		var book = bookService.find(id);
		model.addAttribute("bookDetails", book);

		return "book-show";
	}

	/* Página do Form */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {

		var book = bookService.find(id);
		model.addAttribute("book", book);

		return "admin-book-edit";
	}

	/* Método do Form */
	@PostMapping("/{id}")
	public String update(@PathVariable("id") Long id, Model model, Book book) {

		// TODO: Check if book exists before actions

		bookService.update(book);

		return "redirect:/book/admin";
	}

	@DeleteMapping("/{id}")
	public String destroy(@PathVariable Long id, Model model) {
		bookService.delete(id);

		return "redirect:/book/admin";
	}

	@GetMapping("/search")
	public String search(@RequestParam(name = "query") String query, Model model) {

		List<Book> bookList = bookRepository.searchBookByTitleAndAuthor(query);
		model.addAttribute("bookList", bookList);
		model.addAttribute("searchParam", query);

		return "book-index";
	}

}
