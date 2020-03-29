package com.nikolanedeljkovic.flightadvisor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;
import com.nikolanedeljkovic.flightadvisor.domain.city.City;
import com.nikolanedeljkovic.flightadvisor.repository.AirportRepository;
import com.nikolanedeljkovic.flightadvisor.repository.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

	private final CityRepository cityRepository;
	private final AirportRepository airportRepository;
	
	@Override
	public void uploadAirports(MultipartFile file) {
		try {
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			List<Airport> airports = new ArrayList<>();
			while((line = br.readLine())!=null) {
				line = line.replace("\"", "");
				String[] fields = line.split(",");
				City city = cityRepository.findByName(fields[2]);
				if(cityRepository.findByName(fields[2])!=null) {
					airports.add(Airport.builder()
							.Id(Long.valueOf(fields[0]))
							.name(fields[1])
							.city(city)
							.country(fields[3])
							.IATA(fields[4])
							.ICAO(fields[5])
							.latitude(new BigDecimal(fields[6]))
							.longitude(new BigDecimal(fields[7]))
							.altitude(Integer.valueOf(fields[8]))
							.tzOffset(Integer.valueOf(fields[9]))
							.dst(fields[10].charAt(0))
							.timeZone(TimeZone.getTimeZone(fields[11]))
							.type(fields[12])
							.informationSource(fields[13])
							.build());
				}
			}
			airportRepository.saveAll(airports);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
