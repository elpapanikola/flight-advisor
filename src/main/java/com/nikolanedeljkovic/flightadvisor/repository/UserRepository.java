package com.nikolanedeljkovic.flightadvisor.repository;

import com.nikolanedeljkovic.flightadvisor.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
}
