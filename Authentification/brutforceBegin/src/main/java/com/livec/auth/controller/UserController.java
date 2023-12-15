package com.livec.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livec.auth.dto.UserDto;
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
	public String login(@RequestBody UserDto user) {
		return service.signin(user);
	}
	
	// créer son compte
	@PostMapping("/signup")
	public String signup(@RequestBody UserDto user) {
		return service.signup(user);
	}
	

}
