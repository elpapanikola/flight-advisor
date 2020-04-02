package com.nikolanedeljkovic.flightadvisor.util;

import java.math.BigDecimal;
import java.util.TimeZone;

import org.jgrapht.GraphPath;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;
import com.nikolanedeljkovic.flightadvisor.domain.airport.AirportImport;
import com.nikolanedeljkovic.flightadvisor.domain.airport.Route;
import com.nikolanedeljkovic.flightadvisor.domain.airport.RouteImport;
import com.nikolanedeljkovic.flightadvisor.domain.city.City;
import com.nikolanedeljkovic.flightadvisor.domain.graph.Edge;

public class FieldMapper {

	public static Airport buildDomainAirport(AirportImport a, City city) {

		return Airport.builder().Id(isNullValue(a.getId()) ? null : Long.valueOf(a.getId()))
				.name(isNullValue(a.getName()) ? null : a.getName())
				.city(isNullValue(a.getCity()) ? null : city)
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


	public static Route buildDomainRoute(RouteImport r) {
		return Route.builder().airlineId(isNullValue(r.getAirlineId()) ? null : r.getAirlineId())
				.airline(isNullValue(r.getAirline()) ? null : r.getAirline())
				.sourceAirport(isNullValue(r.getSourceAirport()) ? null : r.getSourceAirport())
				.sourceAirportId(isNullValue(r.getSourceAirportId()) ? null : Long.valueOf(r.getSourceAirportId()))
				.destinationAirport(isNullValue(r.getDestinationAirport()) ? null : r.getDestinationAirport())
				.destinationAirportId(isNullValue(r.getDestinationAirportId()) ? null : Long.valueOf(r.getDestinationAirportId()))
				.codeShare(isNullValue(r.getCodeShare()) ? null : r.getCodeShare().charAt(0))
				.stops(isNullValue(r.getStops()) ? 0 : Integer.valueOf(r.getStops()))
				.equipment(isNullValue(r.getEquipment()) ? null : r.getEquipment())
				.price(isNullValue(r.getPrice()) ? null : Float.valueOf(r.getPrice()))
				.build();
	}
	
	private static boolean isNullValue(String value) {
		if (value == null || value.equals("\\N") || value.equals("")) {
			return true;
		}
		return false;
	}
}
