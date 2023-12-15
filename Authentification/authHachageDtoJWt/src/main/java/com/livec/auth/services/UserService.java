package com.livec.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import com.livec.auth.config.JWTTokenProvider;
import com.livec.auth.dto.UserDto;
import com.livec.auth.models.User;
import com.livec.auth.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public SecurityContextRepository securityContextRepository;
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	
	public void add(User u) {
		repo.save(u);
	}
	
	public List<User> getAll(){
		User u = new User();
		u.setPassword("toto");
		repo.save(u);
		return repo.findAll();
	}
	
	public String signin(UserDto user) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
		    return jwtTokenProvider.createToken(user.getName(), repo.findByName(user.getName()).get().getRole());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public String signup(UserDto u) {
		User user = new User();
		user.setName(u.getName());
		user.setPassword(passwordEncoder.encode(u.getPassword()));
		repo.save(user);
		return jwtTokenProvider.createToken(user.getUsername(), user.getRole());
	}
	

}
