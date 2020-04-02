package com.nikolanedeljkovic.flightadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	
	Route findBySourceAirportAndDestinationAirportAndAirline(String sourceAirport, String destinationAirport, String airline);
	
}
