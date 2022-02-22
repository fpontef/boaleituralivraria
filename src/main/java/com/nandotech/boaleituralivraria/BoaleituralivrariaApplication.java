package com.nandotech.boaleituralivraria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nandotech.boaleituralivraria.entity.Book;
import com.nandotech.boaleituralivraria.entity.User;
import com.nandotech.boaleituralivraria.service.BookService;
import com.nandotech.boaleituralivraria.service.UserService;

@SpringBootApplication
public class BoaleituralivrariaApplication {

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BoaleituralivrariaApplication.class, args);

	}

	/* Populate DB */
	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("*** Populando o DB...");

			// TODO: popular usando data.sql em /src/main/resources
			/* Books */
			Book book1 = new Book("A Volta dos que não foram", "Fulaninho dos Anzóis", 20, 12.50);
			Book book2 = new Book("Poeira em alto mar", "Fulana de Tal", 5, 14.00);
			Book book3 = new Book("Big Badabum", "Cicrano da Silva", 10, 12.00);

			bookService.save(book1);
			bookService.save(book2);
			bookService.save(book3);

			/* Users */
			User admin = new User("Administrador", "admin@asd.com", "admin", "admin", "admin", true);
			User user = new User("Usuário", "user@asd.com", "user", "Rua Aculá, N 123 - Centro", "Sobral", false);

			userService.save(admin);
			userService.save(user);

		};
	}

}
