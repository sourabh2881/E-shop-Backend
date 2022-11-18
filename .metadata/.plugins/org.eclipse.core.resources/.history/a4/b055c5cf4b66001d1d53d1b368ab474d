package com.sourabh.Controller;

import java.util.List;

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

import com.sourabh.Entity.Products;
import com.sourabh.Repository.ProductRepo;
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
		Products prod = prodService.addProduct(req);
		return new ResponseEntity<Products> (prod,HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody ProductUpdateRequest req){
		if(req.getId()==null) {
			GeneralResponse res = new GeneralResponse("Id doesn't Exist");
			return  new ResponseEntity<GeneralResponse> (res,HttpStatus.BAD_REQUEST);
		}
		Products prod = prodService.updateProduct(req);
		if(prod==null) {
			GeneralResponse res = new GeneralResponse("Id doesn't Exist");
			return  new ResponseEntity<GeneralResponse> (res,HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<Products> (prod,HttpStatus.OK);
	}
	
	@GetMapping("/getById/{productId}")
	public ResponseEntity<?> prodById(@PathVariable("productId") int id){
		Products prod = prodService.getById(id);
		if(prod==null) {
			GeneralResponse res = new GeneralResponse("Id doesn't Exist");
			return  new ResponseEntity<GeneralResponse> (res,HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<Products> (prod,HttpStatus.OK);
	}
	
	@GetMapping("/{category}")
	public ResponseEntity<?> prodByCat(@PathVariable String category){
		List<Products> prod = prodService.getByCategory(category);
		return  new ResponseEntity<List<Products>> (prod,HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchString}")
	public ResponseEntity<?> searchString(@PathVariable String searchString) {
		 List<Products> resp= prodService.searchString(searchString);
		 return new ResponseEntity<List<Products>>(resp,HttpStatus.OK);
	}
	
	@PostMapping("/{category}/getFilteredProducts")
	public ResponseEntity<?> filterProducts(@PathVariable String category, @RequestBody FilterRequest req ) {
//		if(req.getName()==null) {
//			List<Products> resp = prodService.filterPrice(req,category);
//			return new ResponseEntity<List<Products>>(resp,HttpStatus.OK);
//		}
//		 List<Products> resp= prodService.filterAll(req,category);
//		 return new ResponseEntity<List<Products>>(resp,HttpStatus.OK);
		List<Products> resp = prodService.filterPrice(req,category);
		return new ResponseEntity<List<Products>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	public List<Products> filterrrProducts() {
		List<Products> productList = prodService.getAllProducts();
		 return productList;
	}
}
