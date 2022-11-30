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
import com.sourabh.Entity.OrderItem;
import com.sourabh.Response.OrderItemResponse;
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
		List<OrderResponse> responseList = orderService.getOrders(userId);
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
		response.setTime(order.getTime());
		List<OrderItemResponse> orderItemList = new ArrayList<>();
		for(OrderItem orderItem : order.getOrderItems()) {
			OrderItemResponse orderItemResponse = new OrderItemResponse();
			orderItemResponse.setProducts(orderItem.getProduct());
			orderItemResponse.setQuantity(orderItem.getQuantity());
			orderItemResponse.setPrice(orderItem.getPrice());
			orderItemList.add(orderItemResponse);
		}
		response.setOrderItems(orderItemList);
		return response;
	}
}
