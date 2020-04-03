package com.nikolanedeljkovic.flightadvisor.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.springframework.stereotype.Service;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;
import com.nikolanedeljkovic.flightadvisor.domain.airport.Route;
import com.nikolanedeljkovic.flightadvisor.domain.graph.Edge;
import com.nikolanedeljkovic.flightadvisor.repository.AirportRepository;
import com.nikolanedeljkovic.flightadvisor.repository.CityRepository;
import com.nikolanedeljkovic.flightadvisor.repository.RouteRepository;
import com.nikolanedeljkovic.flightadvisor.response.PathDTO;
import com.nikolanedeljkovic.flightadvisor.response.RouteDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortestPathServiceImpl implements ShortestPathService {

	private final AirportRepository airportRepository;
	private final RouteRepository routeRepository;
	private final CityRepository cityRepository;

	@Override
	public PathDTO findShortestPath(String sourceCity, String destinationCity) throws NullPointerException {

		List<Airport> sourceAirports = airportRepository.findByCityId(cityRepository.findByName(sourceCity).getId());
		List<Airport> destinationAirports = airportRepository
				.findByCityId(cityRepository.findByName(destinationCity).getId());
		
		DirectedWeightedMultigraph<String, Edge> graph = createGraph();
	
		List<GraphPath<String, Edge>> paths = new ArrayList<>();
		log.info("Searching for shortest route...");
		sourceAirports.stream().forEach(source -> {
			destinationAirports.stream().forEach(dest -> {
				GraphPath<String, Edge> edg = shortestPath(graph, source, dest);
				if (edg != null) {
					paths.add(shortestPath(graph, source, dest));
				}
			});
		});
		Optional<GraphPath<String, Edge>> shortestPath = paths.stream().min(byWeight);
		List<Route> routes = resolveToRoutes(graph, shortestPath);
		log.info("Formating shortest path response...");
		return formatResponse(routes, shortestPath.get().getWeight());
	}

	private PathDTO formatResponse(List<Route> routes, Double weight) {
		List<RouteDTO> routeDto = new ArrayList<>();
		routes.stream().forEach(r -> routeDto.add(RouteDTO.builder()
				.sourceAirportIATA(r.getSourceAirport())
				.sourceAirportName(airportRepository.findByAirportId(r.getSourceAirportId()).getName())
				.destinactionAirportIATA(r.getDestinationAirport())
				.destinationAirportName(airportRepository.findByAirportId(r.getDestinationAirportId()).getName())
				.airline(r.getAirline())
				.sourceCityName(airportRepository.findByAirportId(r.getSourceAirportId()).getCity().getName())
				.destinationCityName(airportRepository.findByAirportId(r.getDestinationAirportId()).getCity().getName())
				.price(r.getPrice()).build()));
		
		
		return PathDTO.builder().routes(routeDto).price(weight).build();
	}

	private GraphPath<String, Edge> shortestPath(DirectedWeightedMultigraph<String, Edge> flightGraph, Airport sourceAirport, Airport desitnationAirport) {
		GraphPath<String, Edge> min = null;
		if (flightGraph.containsVertex(sourceAirport.getIATA())
				&& flightGraph.containsVertex(desitnationAirport.getIATA())) {

			min = DijkstraShortestPath.findPathBetween(flightGraph, sourceAirport.getIATA(),
					desitnationAirport.getIATA());
		}
		return min;
	}

	private DirectedWeightedMultigraph<String, Edge> createGraph() {
		DirectedWeightedMultigraph<String, Edge> flightGraph = new DirectedWeightedMultigraph<>(Edge.class);
		log.info("Creating graph...");
		routeRepository.findAll().stream().forEach(r -> {
			flightGraph.addVertex(r.getDestinationAirport());
			flightGraph.addVertex(r.getSourceAirport());
			if (!r.getSourceAirport().equals(r.getDestinationAirport())) {
				Edge edge = flightGraph.addEdge(r.getSourceAirport(), r.getDestinationAirport());
				edge.setAirline(r.getAirline());
				flightGraph.setEdgeWeight(edge, r.getPrice());
			}
		});
		return flightGraph;
	}

	private List<Route> resolveToRoutes(DirectedWeightedMultigraph<String, Edge> flightGraph, Optional<GraphPath<String, Edge>> shortestPath) {
		List<Route> routes = new ArrayList<>();
		shortestPath.get().getEdgeList().stream().forEach(e -> {
			routes.add(routeRepository.findBySourceAirportAndDestinationAirportAndAirline(flightGraph.getEdgeSource(e), flightGraph.getEdgeTarget(e), e.getAirline()));
		});
		return routes;
	}

	Comparator<GraphPath<String, Edge>> byWeight = (GraphPath<String, Edge> o1, GraphPath<String, Edge> o2) -> Double
			.compare(o1.getWeight(), o2.getWeight());

}
