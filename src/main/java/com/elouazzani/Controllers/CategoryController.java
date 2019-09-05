package com.elouazzani.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elouazzani.entities.Category;
import com.elouazzani.exceptions.ConflictException;
import com.elouazzani.exceptions.NotFoundException;
import com.elouazzani.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = {"", "/"})
	public ResponseEntity<Iterable<Category>> getAll() {
		return new ResponseEntity<>(this.categoryService.findAll(), HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/{id}")
	public Category get(@PathVariable Long id) {
		try {
			return this.categoryService.findById(id);
		} catch (Exception e) {
			throw new NotFoundException(String.format("Requested category with id: %d was not found in our database", id));
		}
	}
	
	@PostMapping(value = {"", "/"})
	public ResponseEntity<Category> save(@RequestBody Category category) {
		if(this.categoryService.findByName(category.getName()) != null) throw new ConflictException("Category with this name alredy exist in database");

		return new ResponseEntity<Category> (this.categoryService.save(category), HttpStatus.CREATED);
	}
	
	@PutMapping(value = {"", "/{id}"})
	public ResponseEntity<Category> category(@PathVariable Long id, @RequestBody Category category) {
		category.setId(id);
		return new ResponseEntity<Category> (this.categoryService.save(category), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			this.categoryService.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException(String.format("Requested category with id: %d was not found in our database", id));
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
