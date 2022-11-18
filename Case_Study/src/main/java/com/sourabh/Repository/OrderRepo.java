package com.sourabh.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.Entity.MyOrder;


public interface OrderRepo extends JpaRepository<MyOrder, Integer>{

	List<MyOrder> findByUserId(int userId);

}
