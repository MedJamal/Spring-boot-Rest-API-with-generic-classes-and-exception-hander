package com.elouazzani.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.elouazzani.services.GenericService;

public class GenericServiceImpl<Entity, Key> implements GenericService<Entity, Key> {

	@Autowired
	CrudRepository<Entity, Key> crudRepository;
	
	@Override
	public Entity save(Entity entity) {
		return this.crudRepository.save(entity);
	}

	@Override
	public Iterable<Entity> saveAll(Iterable<Entity> entities) {
		return this.crudRepository.saveAll(entities);
	}

	@Override
	public Entity findById(Key id) {
		return this.crudRepository.findById(id).get();
	}

	@Override
	public boolean existsById(Key id) {
		return this.crudRepository.existsById(id);
	}

	@Override
	public Iterable<Entity> findAll() {
		return this.crudRepository.findAll();
	}

	@Override
	public Iterable<Entity> findAllById(Iterable<Key> ids) {
		return this.crudRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return this.crudRepository.count();
	}

	@Override
	public void deleteById(Key id) {
		this.crudRepository.deleteById(id);
	}

	@Override
	public void delete(Entity entity) {
		this.crudRepository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<Entity> entities) {
		this.crudRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		this.crudRepository.deleteAll();
	}

}
