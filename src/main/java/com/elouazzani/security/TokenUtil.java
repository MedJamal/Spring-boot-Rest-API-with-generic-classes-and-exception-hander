package com.elouazzani.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

	@Value("${auth.expiration}")
	private Long TOKEN_VALIDITY = 604800L;

	@Value("${auth.secret}")
	private String TOKEN_SECRET = "ThisIsTopSecretKey";

	public String generateToken(UserDetails userDetails) {
		System.out.println(TOKEN_VALIDITY);
		Map<String, Object> claims = new HashMap<>();

		claims.put("sub", userDetails.getUsername());
		claims.put("createdAt", new Date());
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
	}

}
