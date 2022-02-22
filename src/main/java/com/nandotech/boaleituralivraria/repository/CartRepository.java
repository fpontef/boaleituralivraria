package com.nandotech.boaleituralivraria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nandotech.boaleituralivraria.entity.Cart;
import com.nandotech.boaleituralivraria.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllByUser(User user);
	
	Optional<Cart> findByUserAndPaid(User user, Boolean paid);
}
