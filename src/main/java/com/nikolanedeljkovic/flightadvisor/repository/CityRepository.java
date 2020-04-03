package com.nikolanedeljkovic.flightadvisor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nikolanedeljkovic.flightadvisor.domain.city.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	City findByName(String cityName);

	@Query(name = City.GET_ALL_CITIES_WITH_LAST_X_COMMENTS, nativeQuery = true)
	List<City> getAllCitiesWithSpecifiedNumberOfComments(Optional<Integer> numberOfComments);
	
}
