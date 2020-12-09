package com.example.demo.auth.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;

public interface JWTService {
	
	public String create(Authentication auth) throws JsonProcessingException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String getUserName(String token);
	public Collection<GrantedAuthority> getRoles(String token) throws IOException ;
}