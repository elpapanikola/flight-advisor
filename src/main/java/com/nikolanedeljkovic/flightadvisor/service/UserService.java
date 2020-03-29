package com.nikolanedeljkovic.flightadvisor.service;

import com.nikolanedeljkovic.flightadvisor.domain.user.Login;
import com.nikolanedeljkovic.flightadvisor.domain.user.User;

public interface UserService {
	String signUpUser(User user);
	String login(Login login);
}
