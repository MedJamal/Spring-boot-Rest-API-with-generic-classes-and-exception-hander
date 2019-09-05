package com.elouazzani.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.elouazzani.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	public List<Product> findProductByCategoryName(String name);
	
}
