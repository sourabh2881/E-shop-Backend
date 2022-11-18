package com.sourabh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sourabh.Entity.Cart;


@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	Cart findById(int id);
	
//	@Query("SELECT c FROM Cart c WHERE c.user = :user")
//	Cart findByUserId(User user);  

	Cart findByUserId(int uid);
}
