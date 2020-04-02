package com.nikolanedeljkovic.flightadvisor.service;

import com.nikolanedeljkovic.flightadvisor.response.PathDTO;

public interface ShortestPathService {

	PathDTO findShortestPath(String sourceCity, String destinationCity);

}
