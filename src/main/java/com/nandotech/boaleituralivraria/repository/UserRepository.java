package com.nandotech.boaleituralivraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nandotech.boaleituralivraria.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	@Query("SELECT u FROM User WHERE u.email = :email AND u.password := password")
//	public Iterable<User> login(@Param("email") String email, @Param("password") String password);

	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
	
}
