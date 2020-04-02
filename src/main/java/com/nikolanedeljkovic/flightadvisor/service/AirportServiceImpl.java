package com.nikolanedeljkovic.flightadvisor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;
import com.nikolanedeljkovic.flightadvisor.domain.airport.AirportImport;
import com.nikolanedeljkovic.flightadvisor.domain.airport.ImportReader;
import com.nikolanedeljkovic.flightadvisor.domain.airport.Route;
import com.nikolanedeljkovic.flightadvisor.domain.airport.RouteImport;
import com.nikolanedeljkovic.flightadvisor.domain.city.City;
import com.nikolanedeljkovic.flightadvisor.repository.AirportRepository;
import com.nikolanedeljkovic.flightadvisor.repository.CityRepository;
import com.nikolanedeljkovic.flightadvisor.repository.RouteRepository;
import com.nikolanedeljkovic.flightadvisor.util.FieldMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

	private final CityRepository cityRepository;
	private final AirportRepository airportRepository;
	private final RouteRepository routeRepository;

	@Override
	public void uploadAirports(MultipartFile file) {
		try {
			List<String> cities = new ArrayList<>();
			cityRepository.findAll().stream().forEach(c -> cities.add(c.getName()));
			
			ImportReader<AirportImport> reader = new ImportReader<AirportImport>();
			List<AirportImport> importedAirports = reader.read(file.getInputStream(), AirportImport.class);
			List<Airport> airports = new ArrayList<>();
			
			  List<City> citiess = new ArrayList<>(); importedAirports.stream().map(a ->
			  a.getCity()).distinct().forEach(a ->
			  citiess.add(City.builder().name(a).build()));
			  cityRepository.saveAll(citiess); System.out.println(citiess);
			 
			importedAirports.stream()/* .filter(c -> cities.contains(c.getCity())) */
					.forEach(a -> airports.add(FieldMapper.buildDomainAirport(a, cityRepository.findByName(a.getCity()))));
			airportRepository.saveAll(airports);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void uploadRoutes(MultipartFile file) {
		try {
			ImportReader<RouteImport> reader = new ImportReader<RouteImport>();
			List<RouteImport> importedRoutes = reader.read(file.getInputStream(), RouteImport.class);	
			List<Route> routes = new ArrayList<>();
			
			importedRoutes.stream().forEach(r -> routes.add(FieldMapper.buildDomainRoute(r)));
			routeRepository.saveAll(routes);			
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}		
	}
	
}
