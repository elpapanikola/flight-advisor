package com.nikolanedeljkovic.flightadvisor.service;

import java.util.List;
import java.util.Optional;

import com.nikolanedeljkovic.flightadvisor.domain.city.City;
import com.nikolanedeljkovic.flightadvisor.domain.city.Comment;

public interface CityService {
	City addCity(City city);
	Comment postComment(String cityName, Comment comment, String username);
	List<City> getAllCities();
	List<City> getAllCities(Optional<Integer> numberOfComments);
	String deleteComment(Long commentId, String username);
	Comment updateComment(Long commentId, String comment, String username);
}
