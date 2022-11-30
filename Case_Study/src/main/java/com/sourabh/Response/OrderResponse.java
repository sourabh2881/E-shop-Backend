package com.sourabh.Response;

import java.util.Date;
import java.util.List;


public class OrderResponse {
	private int orderId;
	private List<OrderItemResponse> orderItems;
	private boolean status;
	private long price;
	private Date time;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<OrderItemResponse> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemResponse> orderItems) {
		this.orderItems = orderItems;
	}
	
}
