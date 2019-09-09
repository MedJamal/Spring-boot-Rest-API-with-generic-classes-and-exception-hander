package com.elouazzani.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elouazzani.exceptions.BadCredentialsException;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser appUser = userRepository.findByEmail(username);

		if(appUser == null) throw new BadCredentialsException(String.format("User with this email address: %s was not found.", username));

		return appUser;
	}
	
	public Iterable<AppUser> findAll() {
		return this.userRepository.findAll();
	}
	
	public AppUser save(AppUser appUser) {
		
		// Encrypt password
		appUser.setPassword(passwordEncoder().encode(appUser.getPassword()));
		
		return this.userRepository.save(appUser);
	}
	
	public AppUser findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	// Hashing password
	@Bean
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	
	

}
