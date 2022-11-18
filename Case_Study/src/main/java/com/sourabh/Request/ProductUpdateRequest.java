package com.sourabh.Request;

import java.util.ArrayList;
import java.util.List;


public class ProductUpdateRequest {
	private Integer id;	
	private String category;
	private String details;
	private Integer price;
	private String name;

	private List<String> subcategory = new ArrayList<String>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<String> subcategory) {
		this.subcategory = subcategory;
	}

	
}
