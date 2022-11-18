package com.sourabh.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sourabh.Entity.Products;
import com.sourabh.Repository.ProductRepo;
import com.sourabh.Request.FilterRequest;
import com.sourabh.Request.ProductUpdateRequest;
import com.sourabh.Request.ProductsRequest;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelMapper  modelMapper;
	
	public Products addProduct(ProductsRequest req ){
		Products prod = this.modelMapper.map(req, Products.class);
		productRepo.save(prod);
		return prod;	
	}
	
	public Products updateProduct(ProductUpdateRequest req) {
		int id = req.getId();
		Products check = productRepo.findById(id);
		if(check==null) {
			return null;
		}
		Products prod = this.modelMapper.map(req, Products.class);
		productRepo.save(prod);
		return prod;
	}
	
	public Products getById(int id) {
		Products prod = productRepo.findById(id);
		return prod;
	}

	public List<Products> getByCategory(String category) {
		List<Products> prod = productRepo.findByCategory(category);
		return prod;
	}
	
	public List<Products> searchString(String searchString) {
		List<Products> prod = productRepo.searchByNameLike(searchString);
		return prod;
	}

	public List<Products> filterPrice(FilterRequest req, String category) {
		List<Products> prod = productRepo.filterPrice(category,req.getMinPrice(), req.getMaxPrice());
		return prod;
	}
	
//	public List<Products> filterAll(FilterRequest req, String category) {
//		List<Products> prod = productRepo.filterAll(category,req.getName(),req.getMinPrice(), req.getMaxPrice());
//		return prod;
//	}

	public List<Products> getAllProducts() {
		
		return productRepo.findAll();
	}
	
}
