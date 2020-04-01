package com.nikolanedeljkovic.flightadvisor.service;

import org.springframework.web.multipart.MultipartFile;

public interface AirportService {

	void uploadAirports(MultipartFile file);

	void uploadRoutes(MultipartFile file);
	
}
