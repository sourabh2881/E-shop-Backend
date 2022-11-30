package com.sourabh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.Entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

}
