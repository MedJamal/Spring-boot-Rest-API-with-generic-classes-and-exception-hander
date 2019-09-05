package com.elouazzani.services;

import com.elouazzani.entities.Category;

public interface CategoryService extends GenericService<Category, Long> {

	public Category findByName(String name);

}
