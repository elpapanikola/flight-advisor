package com.nikolanedeljkovic.flightadvisor.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nikolanedeljkovic.flightadvisor.service.AirportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RoutesController {
	
	private final AirportService airportService;
	
	@PostMapping("/airport/upload")
	public boolean uploadAirports(@RequestParam("file") MultipartFile file) {
		airportService.uploadAirports(file);
		return true;
	}
}
