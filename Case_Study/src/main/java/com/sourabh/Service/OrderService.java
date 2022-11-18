package com.sourabh.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.Entity.Cart;
import com.sourabh.Entity.CartItem;
import com.sourabh.Entity.MyOrder;
import com.sourabh.Entity.OrderItems;
import com.sourabh.Entity.User;
import com.sourabh.Repository.CartItemRepo;
import com.sourabh.Repository.CartRepo;
import com.sourabh.Repository.OrderRepo;
import com.sourabh.Repository.UserRepo;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private OrderRepo orderRepo;

	public List<MyOrder> getOrders(int userId) {
		List<MyOrder> order = orderRepo.findByUserId(userId);
		return order;
	}

	public MyOrder createOrder(int userId) {
		User user = userRepo.findById(userId);
		MyOrder order = new MyOrder();
		
		List<OrderItems> list_orderItems = new ArrayList<OrderItems>();
		Cart cart = cartRepo.findByUserId(userId);		
		List<CartItem> list_cartItem = cart.getCartItem();
		if(user==null || cart==null || list_cartItem.size()==0) {
			return null;
		}
		
		cart.setCartItem(null);
		cartRepo.save(cart);
		long totalPrice =0;
		order.setUser(user);
		order = orderRepo.save(order);
		for(CartItem cartItem : list_cartItem) {
			totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
			OrderItems orderItems = new OrderItems();
			orderItems.setProduct(cartItem.getProduct());
			orderItems.setOrder(order);
			list_orderItems.add(orderItems);
			cartItemRepo.delete(cartItem);
		}
		order.setTotalPrice(totalPrice);
		order.setStatus(true);
		order.setOrderItems(list_orderItems);
		orderRepo.save(order);
		return order;
	}
}
