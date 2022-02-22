package com.nandotech.boaleituralivraria.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nandotech.boaleituralivraria.entity.Book;
import com.nandotech.boaleituralivraria.entity.Cart;
import com.nandotech.boaleituralivraria.entity.CartItem;
import com.nandotech.boaleituralivraria.entity.User;
import com.nandotech.boaleituralivraria.repository.BookRepository;
import com.nandotech.boaleituralivraria.repository.CartItemRepository;
import com.nandotech.boaleituralivraria.repository.CartRepository;
import com.nandotech.boaleituralivraria.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	public Cart getOpenCart(HttpServletRequest request) {
		User user = getUserData(request);
		Cart openCart = cartRepository.findByUserAndPaid(user, false).orElse(null);

		return openCart;
	}

	public Cart openCart(Long bookId, int bookQty, User user, Cart cart) {

		if (cart == null) {
			cart = cartRepository.save(new Cart(user));
		}

		Book book = getBookData(bookId);

		CartItem cartItem = cartItemRepository.save(new CartItem(book, cart, bookQty, book.getPrice()));

		cart.addCartItems(cartItem);
		
		Cart savedCart = cartRepository.save(cart);

		return savedCart;
	}

	public Cart save(Cart cart) {
		Cart savedCart = cartRepository.save(cart);

		return savedCart;
	}

	public Cart find(Long id) {
		return cartRepository.findById(id).get();
	}

	public List<Cart> list() {
		return cartRepository.findAll();
	}

	public List<Cart> listByUser(HttpServletRequest request) {
		User user = getUserData(request);

		return cartRepository.findAllByUser(user);
	}

	public Cart update(Cart cart) {

		return cartRepository.save(cart);
	}

	private User getUserData(HttpServletRequest request) {

		String userEmail = String.valueOf(CookieService.getCookie(request, "user"));
		
		User user = userRepository.findByEmail(userEmail);

		if (user != null && userEmail.equals(user.getEmail())) {
			return user;
		}

		return null;
	}

	private Book getBookData(Long id) {
		Book book = bookRepository.getById(id);

		return book;
	}

}
