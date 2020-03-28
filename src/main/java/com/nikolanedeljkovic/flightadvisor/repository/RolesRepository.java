package com.nikolanedeljkovic.flightadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikolanedeljkovic.flightadvisor.user.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
	Roles findByRole(String role);
}
