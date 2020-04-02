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

	@Query(value = "SELECT ct.*,a.* FROM comment AS a\r\n" + 
			"  LEFT JOIN comment AS a2 \r\n" + 
			"    ON a.city_id = a2.city_id AND a.created_at <= a2.created_at left join city ct on ct.id = a.city_id\r\n" + 
			"GROUP BY a.id\r\n" + 
			"HAVING COUNT(*) <= 2\r\n" + 
			"ORDER BY a.id, a.created_at desc;", 
			  nativeQuery = true)
	List<City> getAllCitiesWithSpecifiedNumberOfComments(Optional<Integer> numberOfComments);
	
	 @Query("SELECT * FROM City WHERE LOWER(name) LIKE LOWER(concat('%', ?1, '%'))")
	 List<City> findAllLike(String name);
}
