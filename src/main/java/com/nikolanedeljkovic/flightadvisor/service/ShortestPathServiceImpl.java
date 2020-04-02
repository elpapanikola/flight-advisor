package com.nikolanedeljkovic.flightadvisor.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;
import com.nikolanedeljkovic.flightadvisor.domain.graph.Edge;
import com.nikolanedeljkovic.flightadvisor.repository.AirportRepository;
import com.nikolanedeljkovic.flightadvisor.repository.CityRepository;
import com.nikolanedeljkovic.flightadvisor.repository.RouteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortestPathServiceImpl implements ShortestPathService {

	private final AirportRepository airportRepository;
	private final RouteRepository routeRepository;
	private final CityRepository cityRepository;

	@Override
	public void findShortestPath(String sourceCity, String destinationCity) {
		List<Airport> sourceAirports = airportRepository.findByCityId(cityRepository.findByName(sourceCity).getId());
		List<Airport> destinationAirports = airportRepository.findByCityId(cityRepository.findByName(destinationCity).getId());
			
		List<GraphPath<String, Edge>> paths = new ArrayList<>();
		sourceAirports.stream().forEach(source -> {
			destinationAirports.stream().forEach(dest -> {
				paths.add(shortestPath(source, dest));
			});
		});
		paths.stream().forEach(a -> System.out.println(a.getWeight()));
		Optional<GraphPath<String, Edge>> min = paths.stream().min(byWeight);
		System.out.println(min);
		//FieldMapper.mapPath(graph);
	}

	private GraphPath<String, Edge> shortestPath(Airport sourceAirport, Airport desitnationAirport) {
		DirectedWeightedMultigraph<String, Edge> flightGraph = new DirectedWeightedMultigraph<>(
				Edge.class);
		routeRepository.findAll().stream().forEach(r -> {
			flightGraph.addVertex(r.getDestinationAirport());
			flightGraph.addVertex(r.getSourceAirport());
			if (!r.getSourceAirport().equals(r.getDestinationAirport())) {
				Edge edge = flightGraph.addEdge(r.getSourceAirport(), r.getDestinationAirport());
				edge.setAirline(r.getAirline());
				flightGraph.setEdgeWeight(edge, r.getPrice());
			}
		});
		GraphPath<String, Edge> min = null;
		try {
			min = DijkstraShortestPath.findPathBetween(flightGraph, sourceAirport.getIATA(),desitnationAirport.getIATA());
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request! Provide valid input for source and destination city.");
		}
		
		return min;
	}
	
	Comparator<GraphPath<String, Edge>> byWeight = (GraphPath<String, Edge> o1, GraphPath<String, Edge> o2)->Double.compare(o1.getWeight(), o2.getWeight());

}
