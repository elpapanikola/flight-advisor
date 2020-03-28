package com.nikolanedeljkovic.flightadvisor.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nikolanedeljkovic.flightadvisor.repository.RolesRepository;
import com.nikolanedeljkovic.flightadvisor.repository.UserRepository;
import com.nikolanedeljkovic.flightadvisor.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final RolesRepository rolesRepository;
	
	@Transactional
	@Override
	public User signUpUser(User user) throws ResponseStatusException {
		validateUser(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setRoles(List.of(rolesRepository.findByRole("USER")));
		return userRepository.save(user);		
	}

	private void validateUser(User user) throws ResponseStatusException{
		User tmp = userRepository.findByUsername(user.getUsername());
		if(tmp != null ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that username already exist.");
		}
	}
	
	

}
