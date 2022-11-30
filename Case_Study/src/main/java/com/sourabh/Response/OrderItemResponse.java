package com.sourabh.Response;

import com.sourabh.Entity.Product;

public class OrderItemResponse {

	private int quantity;
	private Product products;
	private long price;
	
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProducts() {
		return products;
	}
	public void setProducts(Product products) {
		this.products = products;
	}
	
	
}
