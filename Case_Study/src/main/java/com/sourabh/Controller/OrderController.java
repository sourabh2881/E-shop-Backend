package com.sourabh.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.Entity.MyOrder;
import com.sourabh.Entity.OrderItems;
import com.sourabh.Entity.Products;
import com.sourabh.Response.OrderResponse;
import com.sourabh.Service.OrderService;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/{userId}/getOrders")
	public List<OrderResponse> getOrders(@PathVariable int userId) {
		List<OrderResponse> responseList = new ArrayList<>();
		List<MyOrder> orders = orderService.getOrders(userId);
		if(orders==null) {
			return null;
		}
		for(MyOrder it: orders) {
			OrderResponse response = new OrderResponse();
			response.setOrderId(it.getId());
			response.setStatus(it.isStatus());
			response.setPrice(it.getTotalPrice());
			List<Products> products = new ArrayList<>();
			for(OrderItems orderItems : it.getOrderItems()) {
				products.add(orderItems.getProduct());
			}
			response.setProducts(products);
			responseList.add(response);
		}
		return responseList;
	}
	
	
	
	@GetMapping("/{userId}/createOrder")
	public OrderResponse createOrder (@PathVariable int userId) {
		MyOrder order = orderService.createOrder(userId);
		OrderResponse response = new OrderResponse();
		if(order==null) {
			return response;
		}
		response.setOrderId(order.getId());
		response.setStatus(order.isStatus());
		response.setPrice(order.getTotalPrice());
		List<Products> products = new ArrayList<>();
		for(OrderItems orderItems : order.getOrderItems()) {
			products.add(orderItems.getProduct());
		}
		response.setProducts(products);
		return response;
	}
}
