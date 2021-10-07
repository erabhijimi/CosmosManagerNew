package com.cosmos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmos.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	List<Item> findByProductId(int productId);

}
