package com.nikolanedeljkovic.flightadvisor.web;

import org.jgrapht.GraphPath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nikolanedeljkovic.flightadvisor.domain.graph.Edge;
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
	public boolean uploadAirports(@RequestParam("file") MultipartFile file) {
		airportService.uploadAirports(file);
		return true;
	}
	
	@PostMapping("/upload")
	public boolean uploadRoutes(@RequestParam("file") MultipartFile file) {
		airportService.uploadRoutes(file);
		return true;
	}
	
	@GetMapping("/shortest/{sourceCity}/{destinationCity}")
	public boolean findShortestRoute(@PathVariable String sourceCity, @PathVariable String destinationCity) {
		shortestPathService.findShortestPath(sourceCity, destinationCity);
		return true;
	}

}
