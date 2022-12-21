package com.api.hermina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.password.custom}")
	private String passwordsecret;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (secret.equals(username)) {
			return new User(secret, passwordsecret,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}