package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.UserApp;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserApp user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Usuario no existe");
		}

		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

		for (Role role : user.getRoleList()) {
			authorityList.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorityList);
	}

}
