package com.nikolanedeljkovic.flightadvisor.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikolanedeljkovic.flightadvisor.domain.city.City;
import com.nikolanedeljkovic.flightadvisor.domain.city.Comment;
import com.nikolanedeljkovic.flightadvisor.security.JwtTokenProvider;
import com.nikolanedeljkovic.flightadvisor.service.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CitiyController {
	
	private final CityService cityService;
	private final JwtTokenProvider jwtTokenProvider;
	
	@PostMapping
	public City addCity(@RequestBody City city) {
		return cityService.addCity(city);
	}
	
	@GetMapping({"/cities","/cities/{numberOfComments}"})
	public List<City> getAllCities(@PathVariable Optional<Integer> numberOfComments) {
		if(numberOfComments.isPresent())
			return cityService.getAllCities(numberOfComments);
		return cityService.getAllCities();
	}
	
	@PostMapping("/{cityName}/comment")
	public Comment postComment(@PathVariable String cityName, @RequestBody Comment comment, HttpServletRequest req) {
		String token = jwtTokenProvider.resolveToken(req);
		return cityService.postComment(cityName, comment, jwtTokenProvider.getUsername(token));
	}
	
}
