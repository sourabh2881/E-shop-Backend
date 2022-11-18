package com.sourabh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.Entity.OrderItems;

public interface OrderItemsRepo extends JpaRepository<OrderItems, Integer> {

}
