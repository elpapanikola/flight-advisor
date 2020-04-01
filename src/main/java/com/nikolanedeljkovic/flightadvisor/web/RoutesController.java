package com.nikolanedeljkovic.flightadvisor.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nikolanedeljkovic.flightadvisor.service.AirportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RoutesController {
	
	private final AirportService airportService;
	
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

}
