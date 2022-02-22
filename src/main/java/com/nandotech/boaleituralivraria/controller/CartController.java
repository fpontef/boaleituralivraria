package com.nandotech.boaleituralivraria.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nandotech.boaleituralivraria.entity.Cart;
import com.nandotech.boaleituralivraria.entity.CartItem;
import com.nandotech.boaleituralivraria.entity.User;
import com.nandotech.boaleituralivraria.repository.CartItemRepository;
import com.nandotech.boaleituralivraria.repository.UserRepository;
import com.nandotech.boaleituralivraria.service.CartService;
import com.nandotech.boaleituralivraria.service.CookieService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	/*
	 * path: /cart Consulta se tem usuário logado Consulta se existe algum carrinho
	 * em aberto Caso haja, faz exibição dos itens.
	 */
	@GetMapping("")
	public String index(Model model, HttpServletRequest request) {

		String userEmail = String.valueOf(CookieService.getCookie(request, "user"));

		if (userEmail == "null") {
			model.addAttribute("error", "Necessita fazer o login");
			return "login";
		}

		Cart cart = cartService.getOpenCart(request);

		if (cart == null) {
			model.addAttribute("error", "Carrinho vazio, adicione algum livro");
			return "cart-index";
		}

		List<CartItem> cartItemList = cart.getCartItems();

		model.addAttribute("cartItemList", cartItemList);

		model.addAttribute("cartTotal", cart.getTotal());

		return "cart-index";
	}

	/*
	 * path: /cart/admin Listagem de todos os carrinhos no menu administrativo
	 */
	@GetMapping("/admin")
	public String indexAdmin(Model model) {

		List<Cart> cartList = cartService.list();
		model.addAttribute("cartList", cartList);

		return "admin-cart-index";
	}

	@PostMapping("/add")
	public String add(Model model, HttpServletRequest request, Long bookId, int bookQty) {
		String userEmail = String.valueOf(CookieService.getCookie(request, "user"));

		if (userEmail == "null") {
			model.addAttribute("error", "Necessita fazer o login");
			return "login";
		}

		User user = userRepository.findByEmail(userEmail);

		Cart cart = cartService.getOpenCart(request);

		if (cart == null) {
			// cart == null will create a new cart
			cartService.openCart(bookId, bookQty, user, null);
		} else {
			// cart != null will use the current cart
			cartService.openCart(bookId, bookQty, user, cart);
		}

		return "redirect:/cart";
	}
	
	@GetMapping("/finish")
	public String finish(HttpServletRequest request, Model model) {
		
		Cart cart = cartService.getOpenCart(request);
		cart.setPaid(true);
		
		cartService.save(cart);
		
		return "cart-finish";
	}

	@DeleteMapping("/{cartId}/cartItem/{cartItemId}")
	public String delete(@PathVariable("cartId") Long cartId, @PathVariable("cartItemId") Long cartItemId,
			Model model) {

		Cart cart = cartService.find(cartId);
		CartItem cartItem = cartItemRepository.getById(cartItemId);

		cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));

		cartService.save(cart);
		cartItemRepository.delete(cartItem);

		return "redirect:/cart";
	}

}
