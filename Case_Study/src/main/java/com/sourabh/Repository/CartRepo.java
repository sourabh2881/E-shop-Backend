package com.sourabh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sourabh.Entity.Cart;


@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	Cart findById(int id);
	Cart findByUserId(int uid);
}
