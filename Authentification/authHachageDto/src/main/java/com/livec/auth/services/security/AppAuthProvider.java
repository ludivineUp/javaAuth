package com.livec.auth.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.livec.auth.models.User;
import com.livec.auth.services.UserService;

// AuthenticationManager indique la méthode d'authentification
// IoC de sptring : on délcare les classes, spring qui les instance correctement
public class AppAuthProvider implements AuthenticationManager{
	
	@Autowired
	// IoC de sptring : on délcare les classes, spring qui les instance correctement
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
		// vérifier que le mot est le bon
		// piste : passewordEncoder , méthode matches...
		return new UsernamePasswordAuthenticationToken(name, password, user.getAuthorities());
	}

}
