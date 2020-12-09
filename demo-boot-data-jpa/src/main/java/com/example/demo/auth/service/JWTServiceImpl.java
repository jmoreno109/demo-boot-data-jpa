package com.example.demo.auth.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.example.demo.auth.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService {

	public static final String SECRET = Base64Utils.encodeToString("pepe.12345".getBytes());
	public static final long EXP_DATE = 120000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER = "Authorization";

	@Override
	public String create(Authentication auth) throws JsonProcessingException {

		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(auth.getAuthorities()));

		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(auth.getName())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXP_DATE))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

		return token;
	}

	@Override
	public boolean validate(String token) {

		try {
			getClaims(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody();
		return claims;
	}

	@Override
	public String getUserName(String token) {
		return getClaims(token).getSubject();
	}

	@Override
	public Collection<GrantedAuthority> getRoles(String token) throws IOException {
		Object roles = getClaims(token).get("authorities");
		Collection<GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		return authorities;
	}

}
