package com.elouazzani.services;

public interface GenericService <Entity, Key>{

	Entity save(Entity entity);

	Iterable<Entity> saveAll(Iterable<Entity> entities);

	Entity findById(Key id);

	boolean existsById(Key id);

	Iterable<Entity> findAll();

	Iterable<Entity> findAllById(Iterable<Key> ids);

	long count();

	void deleteById(Key id);

	void delete(Entity entity);

	void deleteAll(Iterable<Entity> entities);

	void deleteAll();

}
