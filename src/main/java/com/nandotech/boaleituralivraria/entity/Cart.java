package com.nandotech.boaleituralivraria.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

//	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems = new ArrayList<CartItem>();

	@Column
	private boolean paid = false;

	public double getTotal() {
		double total = 0.0;
		for (CartItem item : this.getCartItems()) {
			total += item.getPrice();
		}

		return total;
	}

	public Cart() {
	}

	public Cart(User user) {
		this.user = user;
	}

	public Cart(User user, List<CartItem> cartItems) {
		this.user = user;
		this.cartItems = cartItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public void addCartItems(CartItem cartItem) {
		this.cartItems.add(cartItem);
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
