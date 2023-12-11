package com.livec.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livec.auth.models.User;
import com.livec.auth.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
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
		User u = repo.findByName(name).orElse(null);
		if(u != null && u.getPassword().equals(pass)) {
			return true;
		}
		return false;
	}
	
	public String signup(User u) {
		repo.save(u);
		return "";
	}
	

}
