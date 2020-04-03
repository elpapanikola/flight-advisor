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
import org.springframework.web.server.ResponseStatusException;

import com.nikolanedeljkovic.flightadvisor.response.PathDTO;
import com.nikolanedeljkovic.flightadvisor.service.AirportService;
import com.nikolanedeljkovic.flightadvisor.service.ShortestPathService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
@Api("Controller for exposing Airport and Route related operations via REST endpoint.")
public class RoutesController {

	private final AirportService airportService;
	private final ShortestPathService shortestPathService;

	@PostMapping("/airport/upload")
	@ApiOperation("Uploads list of airports from file.")
	public ResponseEntity<String> uploadAirports(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<String>(airportService.uploadAirports(file), HttpStatus.OK);
	}

	@PostMapping("/upload")
	@ApiOperation("Uploads list of routes from file.")
	public ResponseEntity<String> uploadRoutes(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<String>(airportService.uploadRoutes(file), HttpStatus.OK);
	}

	@GetMapping("/shortest/{sourceCity}/{destinationCity}")
	@ApiOperation("Finds cheapest flight for given destination.")
	public PathDTO findShortestRoute(@PathVariable String sourceCity, @PathVariable String destinationCity) {
		try {
			return shortestPathService.findShortestPath(sourceCity, destinationCity);
		} catch (NullPointerException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City provided is not available");
		}
	}

}
