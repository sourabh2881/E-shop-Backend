package com.sourabh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sourabh.Entity.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer>{
	Products findById(int id);
	List<Products> findByCategory(String category);
	
	@Query("SELECT p FROM Products p WHERE p.name LIKE %:searchString% OR p.category LIKE %:searchString%")
	List<Products> searchByNameLike(@Param("searchString") String searchString);
	
	@Query("SELECT p FROM Products p WHERE p.category=:ctgry AND p.price > :minP AND p.price < :maxP")
	List<Products> filterPrice(String ctgry , Long minP , Long maxP);
	
	@Query("SELECT p FROM Products p WHERE p.category=:ctgry AND p.name LIKE %:filterName% AND (p.price > :minP AND p.price < :maxP)")
	List<Products> filterAll(String ctgry , String filterName,Long minP , Long maxP);
}
