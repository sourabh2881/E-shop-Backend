package com.sourabh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourabh.Entity.CartItem;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer>{

	CartItem findByCartId(Integer id);
//
//	List<CartItem> findByProductId(int pid);
	
	
}
