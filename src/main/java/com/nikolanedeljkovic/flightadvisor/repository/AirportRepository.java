package com.nikolanedeljkovic.flightadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
