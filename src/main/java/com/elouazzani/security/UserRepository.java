package com.elouazzani.security;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {

	public AppUser findByEmail(String email);

}
