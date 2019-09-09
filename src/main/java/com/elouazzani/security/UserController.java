package com.elouazzani.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elouazzani.Controllers.BaseController;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"", "/"})
	public ResponseEntity<Iterable<AppUser>> getAll(){
		return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
	}

	
}
