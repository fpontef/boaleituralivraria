package com.nandotech.boaleituralivraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nandotech.boaleituralivraria.entity.Book;
import com.nandotech.boaleituralivraria.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book save(Book book) {
		Book savedBook = bookRepository.save(book);
		return savedBook;
	}

	public Book find(Long id) {
//		Optional<Book> book = ;
//		return Optional.ofNullable(bookRepository.findById(id).get());

		// atenção: retorna um Book ao invés de optional, usando sem tratamento de erros.
		return bookRepository.findById(id).get();
	}

	public List<Book> list() {
		return bookRepository.findAll();
	}

	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

	public Book update(Book book) {

		return bookRepository.save(book);
	}

}
