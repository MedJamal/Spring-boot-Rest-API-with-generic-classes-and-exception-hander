package com.elouazzani.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.elouazzani.security.AppUser;

public class BaseController {
	
	public AppUser getAuthencticatedUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return (AppUser) authentication.getPrincipal();

	}

}
