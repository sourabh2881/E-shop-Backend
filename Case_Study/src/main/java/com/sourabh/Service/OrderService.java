package com.sourabh.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourabh.Entity.Cart;
import com.sourabh.Entity.CartItem;
import com.sourabh.Entity.MyOrder;
import com.sourabh.Entity.OrderItem;
import com.sourabh.Entity.User;
import com.sourabh.Repository.CartItemRepo;
import com.sourabh.Repository.CartRepo;
import com.sourabh.Repository.OrderRepo;
import com.sourabh.Repository.UserRepo;
import com.sourabh.Response.OrderItemResponse;
import com.sourabh.Response.OrderResponse;

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

	public List<OrderResponse> getOrders(int userId) {
		List<OrderResponse> responseList = new ArrayList<>();
		List<MyOrder> orderList = orderRepo.findByUserId(userId);
		if(orderList==null) {
			return null;
		}
		for(MyOrder myOrder: orderList) {
			OrderResponse response = new OrderResponse();
			response.setOrderId(myOrder.getId());
			response.setStatus(myOrder.isStatus());
			response.setPrice(myOrder.getTotalPrice());
			response.setTime(myOrder.getTime());
			List<OrderItemResponse> orderItemList = new ArrayList<>();
			for(OrderItem orderItem : myOrder.getOrderItems()) {
				OrderItemResponse orderItemResponse = new OrderItemResponse();
				orderItemResponse.setProducts(orderItem.getProduct());
				orderItemResponse.setQuantity(orderItem.getQuantity());
				orderItemResponse.setPrice(orderItem.getPrice());
				orderItemList.add(orderItemResponse);
			}
			response.setOrderItems(orderItemList);
			responseList.add(response);
		}
		return responseList;
	}

	public MyOrder createOrder(int userId) {
		User user = userRepo.findById(userId);
		MyOrder order = new MyOrder();
		
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		Cart cart = cartRepo.findByUserId(userId);		
		List<CartItem> cartItemList = cart.getCartItem();
		if(user==null || cart==null || cartItemList.size()==0) {
			return null;
		}
		
		cart.setCartItem(null);
		cartRepo.save(cart);
		long totalPrice =0;
		order.setUser(user);
		order = orderRepo.save(order);
		for(CartItem cartItem : cartItemList) {
			totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			orderItem.setPrice(cartItem.getProduct().getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItemList.add(orderItem);
			cartItemRepo.delete(cartItem);
		}
		order.setTotalPrice(totalPrice);
		order.setStatus(true);
		order.setOrderItems(orderItemList);
		orderRepo.save(order);
		return order;
	}
}
