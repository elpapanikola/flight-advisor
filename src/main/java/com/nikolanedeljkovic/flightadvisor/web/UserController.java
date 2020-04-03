package com.nikolanedeljkovic.flightadvisor.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikolanedeljkovic.flightadvisor.domain.user.Login;
import com.nikolanedeljkovic.flightadvisor.domain.user.User;
import com.nikolanedeljkovic.flightadvisor.service.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Api("Controller for exposing User related operations via REST endpoint.")
public class UserController {
	
	private final UserServiceImpl userService;
	
	@PostMapping("/signup")
	@ApiOperation("Registering user.")
	public String signUpUser(@RequestBody User user) {
		return userService.signUpUser(user);
	}
	
	@PostMapping("/login")
	@ApiOperation("Loging in user.")
	public String logIn(@RequestBody Login login) {
		return userService.login(login);
	}
	
}
