package com.nandotech.boaleituralivraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nandotech.boaleituralivraria.entity.Book;
import com.nandotech.boaleituralivraria.entity.Cart;
import com.nandotech.boaleituralivraria.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	public List<CartItem> findByCart(Cart cart);

	public CartItem findByCartAndBook(Cart cart, Book book);
}
