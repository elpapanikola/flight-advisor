package com.nikolanedeljkovic.flightadvisor.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;
import com.nikolanedeljkovic.flightadvisor.domain.airport.AirportImport;
import com.nikolanedeljkovic.flightadvisor.repository.AirportRepository;
import com.nikolanedeljkovic.flightadvisor.repository.CityRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

	private final CityRepository cityRepository;
	private final AirportRepository airportRepository;

	@Override
	public void uploadAirports(MultipartFile file) {
		try {

			List<String> cities = new ArrayList<>();
			cityRepository.findAll().stream().forEach(c -> cities.add(c.getName()));

			List<AirportImport> importedAirports = readAirports(file.getInputStream());
			List<Airport> airports = new ArrayList<>();
			importedAirports.parallelStream().filter(c -> cities.contains(c.getCity()))
					.forEach(a -> airports.add(buildDomainAirport(a)));
			airportRepository.saveAll(airports);

		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void uploadRoutes(MultipartFile file) {
		// TODO Auto-generated method stub

	}

	private Airport buildDomainAirport(AirportImport a) {

		return Airport.builder().Id(isNullValue(a.getId()) ? null : Long.valueOf(a.getId()))
				.name(isNullValue(a.getName()) ? null : a.getName())
				.city(isNullValue(a.getCity()) ? null : cityRepository.findByName(a.getCity()))
				.country(isNullValue(a.getCountry()) ? null : a.getCountry())
				.altitude(isNullValue(a.getLatitude()) ? null : Integer.valueOf(a.getAltitude()))
				.latitude(isNullValue(a.getAltitude()) ? null : new BigDecimal(a.getLatitude()))
				.longitude(isNullValue(a.getLongitude()) ? null : new BigDecimal(a.getLongitude()))
				.IATA(isNullValue(a.getIATA()) ? null : a.getIATA()).ICAO(isNullValue(a.getICAO()) ? null : a.getICAO())
				.dst(isNullValue(a.getDst()) ? null : a.getDst().charAt(0))
				.tzOffset(isNullValue(a.getTzOffset()) ? null : Float.valueOf(a.getTzOffset()))
				.timeZone(isNullValue(a.getTimeZone()) ? null : TimeZone.getTimeZone(a.getTimeZone()))
				.type(isNullValue(a.getType()) ? null : a.getType())
				.informationSource(isNullValue(a.getInformationSource()) ? null : a.getInformationSource()).build();
	}

	private List<AirportImport> readAirports(InputStream file) {
		CsvToBean<AirportImport> airportParser = new CsvToBeanBuilder<AirportImport>(
				new InputStreamReader(file, StandardCharsets.UTF_8)).withType(AirportImport.class)
						.withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).withEscapeChar(':').build();
		return airportParser.parse();
	}

	
	private boolean isNullValue(String value) {
		if (value == null || value.equals("\\N") || value.equals("")) {
			return true;
		}
		return false;
	}

}
