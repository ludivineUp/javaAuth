package com.livec.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.livec.auth.models.User;
import com.livec.auth.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	public void add(User u) {
		repo.save(u);
	}
	
	public List<User> getAll(){
		User u = new User();
		u.setPassword("toto");
		repo.save(u);
		return repo.findAll();
	}
	
	public Boolean signin(String name, String pass) {
		// si bon name et pass
		/*User u = repo.findByName(name).orElse(null);
		if(u != null && u.getPassword().equals(pass)) {
			return true;
		}*/
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, pass));
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public String signup(User u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		repo.save(u);
		return "";
	}
	

}
