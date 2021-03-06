package com.nikolanedeljkovic.flightadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikolanedeljkovic.flightadvisor.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
}
