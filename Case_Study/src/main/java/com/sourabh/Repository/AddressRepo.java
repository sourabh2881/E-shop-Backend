package com.sourabh.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourabh.Entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
