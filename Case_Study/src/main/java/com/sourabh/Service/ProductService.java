package com.sourabh.Service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	@Autowired
	EntityManager em; 
	
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
		String trimmed = searchString.replaceAll("\\s{2,}", " ").trim();
		String[] searchArray = trimmed.split(" ");
		if(searchArray.length==0) {
			return productRepo.findAll();
		}
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Products> cq = cb.createQuery(Products.class);
		Root<Products> productRoot = cq.from(Products.class);
		List<Predicate> predicates = new LinkedList<>();
		
		Predicate byName = cb.like(productRoot.get("name"), "%" + searchArray[0] + "%");
		Predicate byCategory = cb.like(productRoot.get("category"), "%" + searchArray[0] + "%");
		Predicate result = cb.or(byName,byCategory);

		for(int i=1; i<searchArray.length;i++) {
			System.out.println(searchArray[i]);
			byCategory = cb.like(productRoot.get("category"), "%" + searchArray[i] + "%");
			result = cb.or(result,byCategory);
			byName = cb.like(productRoot.get("name"), "%" + searchArray[i] + "%");
			result = cb.or(result,byName);
		}
		
		predicates.add(result);
		Predicate[] predArray = new Predicate[predicates.size()];
		predicates.toArray(predArray);
		cq.where(predArray);
		TypedQuery<Products> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Products> filterPrice(FilterRequest req, String category) {
		List<Products> prod = productRepo.filterPrice(category,req.getMinPrice(), req.getMaxPrice());
		return prod;
	}

	public List<Products> getAllProducts() {
		return productRepo.findAll();
	}
	
}
