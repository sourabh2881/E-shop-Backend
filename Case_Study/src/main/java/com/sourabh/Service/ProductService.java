package com.sourabh.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sourabh.Entity.Product;
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
	
	public Product addProduct(ProductsRequest req ){
		Product prod = this.modelMapper.map(req, Product.class);
		productRepo.save(prod);
		return prod;	
	}
	
	public Product updateProduct(ProductUpdateRequest req) {
		int id = req.getId();
		Product check = productRepo.findById(id);
		if(check==null) {
			return null;
		}
		Product prod = this.modelMapper.map(req, Product.class);
		productRepo.save(prod);
		return prod;
	}
	
	public Product getById(int id) {
		Product prod = productRepo.findById(id);
		return prod;
	}

	public List<Product> getByCategory(String category) {
		
		List<Product> prod = productRepo.findByCategory(category);
		return prod;
	}
	
	public List<Product> searchString(String searchString) {
		String trimmed = searchString.replaceAll("\\s{2,}", " ").trim();
		String[] searchArray = trimmed.split(" ");
		if(searchArray.length==1) {
			return productRepo.findAll();
		}
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> productRoot = cq.from(Product.class);
		List<Predicate> predicates = new LinkedList<>();
		
		Predicate byName = cb.like(productRoot.get("name"), "%" + searchArray[0] + "%");
		Predicate byCategory = cb.like(productRoot.get("category"), "%" + searchArray[0] + "%");
		Predicate result = cb.or(byName,byCategory);

		for(int i=1; i+1<searchArray.length;i++) {
			byCategory = cb.like(productRoot.get("category"), "%" + searchArray[i] + "%");
			result = cb.or(result,byCategory);
			byName = cb.like(productRoot.get("name"), "%" + searchArray[i] + "%");
			result = cb.or(result,byName);
		}
		
		predicates.add(result);
		Predicate[] predArray = new Predicate[predicates.size()];
		predicates.toArray(predArray);
		cq.where(predArray);
		TypedQuery<Product> query = em.createQuery(cq);
		return query.getResultList();
	}

	public List<Product> filterPrice(FilterRequest req, String category) {
		if(category.equalsIgnoreCase("All")) {
			category="";
		}
		List<Product> prod = productRepo.filterPrice(category,req.getMinPrice()-1, req.getMaxPrice()+1);
		return prod;
	}

	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public SortedSet<String> getAllCategories() {
		List<Product> products = productRepo.findAll();
		SortedSet<String> categories = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		for(Product product: products) {
			categories.add(product.getCategory());
		}
		return categories;
	}
	
}
