package com.sourabh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sourabh.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	Product findById(int id);
	List<Product> findByCategory(String category);
	
	@Query("SELECT p FROM Product p WHERE p.category LIKE %:ctgry%  AND p.price > :minP AND p.price < :maxP")
	List<Product> filterPrice(@Param("ctgry")String category , @Param("minP")Long minPrice , @Param("maxP")Long maxPrice);
	
}
