package com.livec.auth.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.livec.auth.models.User;
import com.livec.auth.services.UserService;

public class AppAuthProvider implements AuthenticationManager{
	
	@Autowired
	private UserAuthService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String name = auth.getName();
		String password = auth.getCredentials().toString(); // a verif
		User user = (User) userService.loadUserByUsername(name);
		if(user == null) {
			throw new BadCredentialsException("log/pass inconnu");
		}
		return new UsernamePasswordAuthenticationToken(name, password, user.getAuthorities());
	}

}
