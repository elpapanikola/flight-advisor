package com.nikolanedeljkovic.flightadvisor.service;

import org.springframework.web.multipart.MultipartFile;

public interface AirportService {

	String uploadAirports(MultipartFile file);

	String uploadRoutes(MultipartFile file);
	
}
