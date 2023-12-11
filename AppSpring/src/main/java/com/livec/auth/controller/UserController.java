package com.livec.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livec.auth.models.User;
import com.livec.auth.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/add")
	public User add(@RequestBody User user) {
		service.add(user);
		return user;
	}
	
	// se connecter
	@PostMapping("/signin")
	public Boolean login(@RequestParam("username") String username, @RequestParam("pass") String password) {
		return service.signin(username, password);
	}
	
	// créer son compte
	@PostMapping("/signup")
	public String signup(@RequestBody User user) {
		return service.signup(user);
	}
	

}
