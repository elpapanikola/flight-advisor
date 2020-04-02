package com.nikolanedeljkovic.flightadvisor.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDTO {
	String airline;
	String sourceAirportIATA;
	String sourceAirportName;
	String sourceCityName;
	String destinactionAirportIATA;
	String destinationAirportName;
	String destinationCityName;
	Float price;
}
