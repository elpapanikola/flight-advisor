package com.nikolanedeljkovic.flightadvisor.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/api/cities")
public class CityController {
	
	private final CityService cityService;
	private final JwtTokenProvider jwtTokenProvider;
	
	@PostMapping
	public City addCity(@RequestBody City city) {
		return cityService.addCity(city);
	}
	
	@GetMapping({"/all","/all/{numberOfComments}"})
	public List<City> getAllCities(@PathVariable Optional<Integer> numberOfComments) {
		if(numberOfComments.isPresent())
			return cityService.getAllCities(numberOfComments);
		return cityService.getAllCities();
	}
	
	@PostMapping("/{cityName}/comment")
	public ResponseEntity<Comment> postComment(@PathVariable String cityName, @RequestBody Comment comment, HttpServletRequest req) {
		String token = jwtTokenProvider.resolveToken(req);
		return new ResponseEntity<Comment>(cityService.postComment(cityName, comment, jwtTokenProvider.getUsername(token)),HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deleteComment( @PathVariable Long commentId) {
		return new ResponseEntity<String>(cityService.deleteComment(commentId), HttpStatus.OK);
	}
	
	@PutMapping("/{commentId}}")
	public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody String comment){
		return new ResponseEntity<Comment>(cityService.updateComment(commentId, comment), HttpStatus.OK);
	}
}
