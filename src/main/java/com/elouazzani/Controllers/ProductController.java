package com.elouazzani.Controllers;

import java.util.List;

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

import com.elouazzani.entities.Product;
import com.elouazzani.exceptions.NotFoundException;
import com.elouazzani.services.CategoryService;
import com.elouazzani.services.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = {"", "/"})
	public ResponseEntity<Iterable<Product>> getAll(){
		return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = {"/{id}"})
	public Product getById(@PathVariable Long id) {
		try {
			return this.productService.findById(id);
		} catch (Exception e) {
			throw new NotFoundException(String.format("Requested data [product] with id: %d was not found in database.", id));
		}
	}
	
	@PostMapping(value = {"", "/"})
	public ResponseEntity<Product> save(@RequestBody Product product){
		
		try {
			this.categoryService.findById(product.getCategory().getId());
		} catch (Exception e) {
			throw new NotFoundException("Category not found");
		}
		
		return new ResponseEntity<>(this.productService.save(product), HttpStatus.CREATED);
	}
	
	@PostMapping("/save-all")
	public ResponseEntity<Iterable<Product>> saveAll(List<Product> products) {
		// TODO : Test this method
		
		return new ResponseEntity<>(this.productService.saveAll(products), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id) {
		
		if(!this.productService.existsById(id)) throw new NotFoundException(String.format("Requested data [product] with id: %d was not found in database.", id));

		product.setId(id);
		
		return new ResponseEntity<>(this.productService.save(product), HttpStatus.OK);
	}
	
	@DeleteMapping(value = {"", "/{id}"})
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(!this.productService.existsById(id)) throw new NotFoundException(String.format("Requested data [product] with id: %d was not found in database.", id));

		this.productService.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}