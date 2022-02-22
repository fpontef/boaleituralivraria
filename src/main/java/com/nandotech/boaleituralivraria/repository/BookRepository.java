package com.nandotech.boaleituralivraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nandotech.boaleituralivraria.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
//	@Query("SELECT u FROM User WHERE u.email = :email AND u.password := password")
	@Query( value = "SELECT * FROM book ORDER BY id DESC LIMIT 0,3", nativeQuery = true )
	public List<Book> getNewArrival();

	@Query( value = "SELECT * FROM book WHERE title ILIKE %:searchParam% OR author ILIKE %:searchParam%", nativeQuery = true )
	public List<Book> searchBookByTitleAndAuthor(@Param("searchParam") String searchParam);
	
}
