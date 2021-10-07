package com.cosmos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.model.UserCart;

public interface UserCartRepository  extends JpaRepository<UserCart, Long>{

}
