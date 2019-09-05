package com.elouazzani.repositories;

import org.springframework.data.repository.CrudRepository;

import com.elouazzani.entities.Category;


public interface CategoryRepository  extends CrudRepository<Category, Long>{
	
	public Category findByName(String name);

}
