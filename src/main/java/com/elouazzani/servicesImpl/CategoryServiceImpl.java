package com.elouazzani.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elouazzani.entities.Category;
import com.elouazzani.repositories.CategoryRepository;
import com.elouazzani.services.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Long> implements CategoryService {

	@Autowired
	CategoryRepository CategoryRepository;
	
	@Override
	public Category findByName(String name) {
		return this.CategoryRepository.findByName(name);
	}


}
