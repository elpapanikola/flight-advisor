package com.nikolanedeljkovic.flightadvisor.service;

import org.jgrapht.GraphPath;

import com.nikolanedeljkovic.flightadvisor.domain.graph.Edge;

public interface ShortestPathService {

	void findShortestPath(String sourceCity, String destinationCity);

}
