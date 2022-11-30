package com.sourabh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.Entity.Cart;
import com.sourabh.Entity.CartItem;
import com.sourabh.Entity.Product;
import com.sourabh.Request.ProductQuantity;
import com.sourabh.Response.GeneralResponse;
import com.sourabh.Service.CartService;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/{userId}/getCart")
	public List<CartItem> getCart(@PathVariable("userId") int uid) {
		Cart cart = cartService.getCart(uid);
		if(cart==null) {
			return null;
		}
		return cart.getCartItem();
	}
	
	@GetMapping("/{userId}/getCartItem/{cartitemId}")
	public CartItem getCartItem(@PathVariable("userId") int uid,@PathVariable int cartitemId) {
		CartItem cartItem = cartService.getCartItem(uid,cartitemId);
		return cartItem;
	}
	
	@GetMapping("/{userId}/add/{productId}")
	public CartItem addItem(@PathVariable("userId") int uid, @PathVariable("productId") int pid ){
		CartItem cartitem = cartService.addCart(uid,pid);
		return cartitem;
	}
	
	@GetMapping("/{userId}/remove/{productId}")
	public GeneralResponse removeItem(@PathVariable("userId") int uid, @PathVariable("productId") int pid ){
		Product prod= cartService.removeItem(uid,pid);
		if(prod==null) {
			GeneralResponse response = new GeneralResponse("Product doesn't exist for the user.");
			return response;
		}
		GeneralResponse response = new GeneralResponse(prod.getName() + " removed from cart");
		return response;
	}
	
	@PostMapping("/{userId}/changeQuantity/{productId}")
	public CartItem changeQuantity(@PathVariable("userId") int uid, @PathVariable("productId") int pid, @RequestBody ProductQuantity prodQuantity){
		CartItem cartItem= cartService.changeQuantity(uid,pid,prodQuantity.getQuantity());
		return cartItem;
	}
}
