package com.nikolanedeljkovic.flightadvisor.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikolanedeljkovic.flightadvisor.domain.user.Login;
import com.nikolanedeljkovic.flightadvisor.domain.user.User;
import com.nikolanedeljkovic.flightadvisor.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserServiceImpl userService;
	
	@PostMapping("/signup")
	public String signUpUser(@RequestBody User user) {
		return userService.signUpUser(user);
	}
	
	@PostMapping("/login")
	public String logIn(@RequestBody Login login) {
		return userService.login(login);
	}
	
}
