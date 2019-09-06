package com.elouazzani.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

	public String getUserNameFromToken(String token) {

		Claims claims;
		try {
			claims = getClaims(token);
			return claims.getSubject();
		} catch (Exception e) {
			claims = null;
		}

		return null;
	}

	private Claims getClaims(String token) {

		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = getUserNameFromToken(token);
		if(username.equals(userDetails.getUsername()) && !isTokenExpired(token)) return true;
		return false;		
	}

	private boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();
		return expiration.before(new Date());
	}

}


