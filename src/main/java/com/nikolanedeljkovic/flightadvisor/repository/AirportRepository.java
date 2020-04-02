package com.nikolanedeljkovic.flightadvisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
	@Query("SELECT a FROM Airport a WHERE ID = ?1")
	Airport findByAirportId(Long id);
	
	List<Airport> findByCityId(Long cityId);
}
