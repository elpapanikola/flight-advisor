package com.nikolanedeljkovic.flightadvisor.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nikolanedeljkovic.flightadvisor.domain.user.Login;
import com.nikolanedeljkovic.flightadvisor.domain.user.Roles;
import com.nikolanedeljkovic.flightadvisor.domain.user.User;
import com.nikolanedeljkovic.flightadvisor.repository.RolesRepository;
import com.nikolanedeljkovic.flightadvisor.repository.UserRepository;
import com.nikolanedeljkovic.flightadvisor.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private  BCryptPasswordEncoder passwordEncoder;
	private RolesRepository rolesRepository;
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	@Transactional
	public String signUpUser(User user) throws ResponseStatusException {
		validateSignup(user);
		List<Roles> roles = new ArrayList<>();
		roles.add(rolesRepository.findByRole("USER"));
		user.setPassword(passwordEncoder.encode(user.getPassword()));		
		user.setRoles(roles);
		userRepository.save(user);		
		return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
	}

	@Override
	public String login(Login login) {
		User user = userRepository.findByUsername(login.getUsername());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password are incorrect.");
		}
		if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or password are incorrect.");
		}
		return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
	}
	
	
	private void validateSignup(User user) throws ResponseStatusException{
		User tmp = userRepository.findByUsername(user.getUsername());
		if(tmp != null ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that username already exist.");
		}
	}
	

}
