package com.sourabh.Request;

public class FilterRequest {
	private String name;
	private long minPrice;
	private long maxPrice;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(long minPrice) {
		this.minPrice = minPrice;
	}
	public long getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(long maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}
