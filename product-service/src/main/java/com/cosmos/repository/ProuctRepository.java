package com.cosmos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cosmos.model.Product;

public interface ProuctRepository extends JpaRepository<Product, Long>{

	@Query("SELECT c FROM Product c WHERE c.active = true")
	public List<Product> findAllEnabled();
	@Query("SELECT c FROM Product c WHERE c.active = true and c.addedToCart = false")
	public List<Product> findAllEnabledAndStore();
	@Query("SELECT c FROM Product c WHERE c.active = false")
	public List<Product> findAllDissabled();
}
