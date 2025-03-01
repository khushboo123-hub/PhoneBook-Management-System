package com.dollop.app.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.dtos.LoginRequest;
import com.dollop.app.entity.User;
import com.dollop.app.service.IUserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("save")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		User usr = userService.registerUser(user);
		ResponseEntity<User> response = new ResponseEntity<User>(usr,HttpStatus.CREATED);
		return response;
	}
	
	@PostMapping("login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody LoginRequest request){
		return new ResponseEntity<> (userService.loginUser(request.getUserName(),request.getPassword()),HttpStatus.CREATED);
	}
}
