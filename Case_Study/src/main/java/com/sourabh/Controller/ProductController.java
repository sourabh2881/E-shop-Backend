package com.sourabh.Controller;

import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourabh.Entity.Product;
import com.sourabh.Request.FilterRequest;
import com.sourabh.Request.ProductUpdateRequest;
import com.sourabh.Request.ProductsRequest;
import com.sourabh.Response.GeneralResponse;
import com.sourabh.Service.ProductService;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService prodService;

	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody ProductsRequest req){
		Product prod = prodService.addProduct(req);
		return new ResponseEntity<Product> (prod,HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody ProductUpdateRequest req){
		if(req.getId()==null) {
			GeneralResponse res = new GeneralResponse("Id doesn't Exist");
			return  new ResponseEntity<GeneralResponse> (res,HttpStatus.BAD_REQUEST);
		}
		Product prod = prodService.updateProduct(req);
		if(prod==null) {
			GeneralResponse res = new GeneralResponse("Id doesn't Exist");
			return  new ResponseEntity<GeneralResponse> (res,HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<Product> (prod,HttpStatus.OK);
	}
	
	@GetMapping("/getById/{productId}")
	public ResponseEntity<?> productById(@PathVariable("productId") int id){
		Product prod = prodService.getById(id);
		if(prod==null) {
			GeneralResponse res = new GeneralResponse("Id doesn't Exist");
			return  new ResponseEntity<GeneralResponse> (res,HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<Product> (prod,HttpStatus.OK);
	}
	
	@GetMapping("/{category}")
	public ResponseEntity<?> productByCategory(@PathVariable String category){
		List<Product> prod = prodService.getByCategory(category);
		return  new ResponseEntity<List<Product>> (prod,HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchString}")
	public ResponseEntity<?> searchString(@PathVariable String searchString) {
		 List<Product> resp= prodService.searchString(searchString);
		 return new ResponseEntity<List<Product>>(resp,HttpStatus.OK);
	}
	
	@PostMapping("/{category}/getFilteredProducts")
	public ResponseEntity<?> filterProducts(@PathVariable String category, @RequestBody FilterRequest req ) {
		List<Product> resp = prodService.filterPrice(req,category);
		return new ResponseEntity<List<Product>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		List<Product> productList = prodService.getAllProducts();
		 return productList;
	}
	
	@GetMapping("/getAllCategories")
	public SortedSet<String> filterrrProducts() {	
		 return prodService.getAllCategories();
	}
}
