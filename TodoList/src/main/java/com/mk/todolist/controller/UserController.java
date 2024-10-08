package com.mk.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.todolist.dao.UsersDao;
import com.mk.todolist.service.UserService;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	@PostMapping("/create")
	public  ResponseEntity<UsersDao> createUser(@RequestBody UsersDao usersdao) {
		//TODO: process POST request
		
		return new ResponseEntity<>(userservice.createUserDetails(usersdao),HttpStatus.CREATED);
	}
	
}
