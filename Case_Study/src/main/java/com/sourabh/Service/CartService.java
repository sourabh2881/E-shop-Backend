package com.sourabh.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.Entity.Cart;
import com.sourabh.Entity.CartItem;
import com.sourabh.Entity.Product;
import com.sourabh.Entity.User;
import com.sourabh.Repository.CartItemRepo;
import com.sourabh.Repository.CartRepo;
import com.sourabh.Repository.ProductRepo;
import com.sourabh.Repository.UserRepo;
@Service
public class CartService {

	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	CartItemRepo cartItemRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserRepo userRepo;
	
	public Cart getCart(int uid) {
		Cart cart = cartRepo.findByUserId(uid);
		return cart;
	}
	
	public CartItem addCart(int uid, int pid) {
		User user = userRepo.findById(uid);
		Product product = productRepo.findById(pid);
		Cart c1 = cartRepo.findByUserId(uid);
		
		// If User Cart doesn't exist
		if(c1==null) {
			Cart cart = new Cart();
			cart.setUser(user);
			cart = cartRepo.save(cart);
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			List<CartItem> ci = new ArrayList<>();
			ci.add(cartItem);
			cart.setCartItem(ci);
			cartRepo.save(cart);
			return cartItem;
		}
		
		//If User Cart exists
		else {
			List<CartItem> list = c1.getCartItem();
			// If Cart Item exists in cart, increasing quantity by 1.
			for(CartItem ci : list) {
				if(ci.getProduct().getId()==pid) {
					ci.setQuantity(ci.getQuantity()+1);
					cartRepo.save(c1);
					int size = c1.getCartItem().size();
					return c1.getCartItem().get(size-1);
				}
			}
			// If Cart Item dosen't exist in cart
			CartItem cartItem = new CartItem();
			cartItem.setCart(c1);
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			list.add(cartItem);
			c1.setCartItem(list);
			cartRepo.save(c1);
			int size = c1.getCartItem().size();
			return c1.getCartItem().get(size-1);
		}
	}

	public CartItem getCartItem(int uid, int cartitemId) {
		Cart cart = cartRepo.findByUserId(uid);
		List<CartItem> cartItemList = cart.getCartItem();
		for(CartItem cartItem : cartItemList) {
			if(cartItem.getId()==cartitemId) {
				return cartItem;
			}
		}
		return null;
	}

	public Product removeItem(int uid, int pid) {
		Cart cart = cartRepo.findByUserId(uid);
		Product product = productRepo.findById(pid);
		CartItem remove = new CartItem();
		List<CartItem> citems = cartItemRepo.findByProductId( pid);
		for(CartItem ci : citems) {
			if(ci.getCart()==cart) {
				remove=ci;
				break;
			}
		}
		if(cart==null || product==null || remove.getId()==null) {
			return null;
		}
		cartItemRepo.delete(remove);
		return product;
	}

	public CartItem changeQuantity(int uid, int pid,int quantity) {
		Cart cart = cartRepo.findByUserId(uid);
		List<CartItem> cartItemList = cart.getCartItem();
		Product product = productRepo.findById(pid);
		for(CartItem cartItem : cartItemList) {
			if(cartItem.getProduct().equals(product)) {
				cartItem.setQuantity(quantity);
				cart.setCartItem(cartItemList);
				cartRepo.save(cart);
				return cartItem;
			}
		}
		return null;
	}

}