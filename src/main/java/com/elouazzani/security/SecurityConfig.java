package com.elouazzani.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final String[] PUBLIC_ROUTES = {
			"/api/auth/**",
			"/api/product/**"
	};

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		System.out.println("AuthenticationManager");
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and().csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers(PUBLIC_ROUTES).permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic();
	}

}