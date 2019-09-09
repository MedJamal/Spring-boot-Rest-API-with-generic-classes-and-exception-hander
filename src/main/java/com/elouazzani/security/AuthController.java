package com.elouazzani.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elouazzani.exceptions.ConflictException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = this.userService.loadUserByUsername(signInRequest.getUsername());
		
		String token = tokenUtil.generateToken(userDetails);

		return new JwtResponse(token);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<AppUser> signIn(@RequestBody SignUpRequest signUpRequest) {
		
		if(this.userService.findByEmail(signUpRequest.getEmail()) != null) throw new ConflictException(String.format("User with this email: %s already exist.", signUpRequest.getEmail()));
		
		AppUser user = new AppUser(signUpRequest.getEmail(), signUpRequest.getPassword(), signUpRequest.getName());
		
        return new ResponseEntity<AppUser>(this.userService.save(user), HttpStatus.CREATED);
	}
	
}
