package com.nikolanedeljkovic.flightadvisor.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nikolanedeljkovic.flightadvisor.response.PathDTO;
import com.nikolanedeljkovic.flightadvisor.service.AirportService;
import com.nikolanedeljkovic.flightadvisor.service.ShortestPathService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RoutesController {
	
	private final AirportService airportService;
	private final ShortestPathService shortestPathService;
	
	@PostMapping("/airport/upload")
	public ResponseEntity<String> uploadAirports(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<String>(airportService.uploadAirports(file), HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadRoutes(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<String>(airportService.uploadRoutes(file), HttpStatus.OK);
	}
	
	@GetMapping("/shortest/{sourceCity}/{destinationCity}")
	public PathDTO findShortestRoute(@PathVariable String sourceCity, @PathVariable String destinationCity) {
		return shortestPathService.findShortestPath(sourceCity, destinationCity);		
	}

}
