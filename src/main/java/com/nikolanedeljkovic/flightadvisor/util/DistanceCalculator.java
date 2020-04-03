package com.nikolanedeljkovic.flightadvisor.util;

import java.math.BigDecimal;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;

public class DistanceCalculator {

	public static double distance(Airport sourceAirport, Airport destinationAirport) {
		if ((sourceAirport.getLatitude() == destinationAirport.getLatitude())
				&& (sourceAirport.getLongitude() == destinationAirport.getLongitude())) {
			return 0;
		} else {
			BigDecimal theta = sourceAirport.getLongitude().subtract(destinationAirport.getLongitude());
			double dist = Math.sin(Math.toRadians(sourceAirport.getLatitude().doubleValue()))
					* Math.sin(Math.toRadians(destinationAirport.getLatitude().doubleValue()))
					+ Math.cos(Math.toRadians(sourceAirport.getLatitude().doubleValue()))
							* Math.cos(Math.toRadians(destinationAirport.getLatitude().doubleValue()))
							* Math.cos(Math.toRadians(theta.doubleValue()));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return (dist);
		}
	}
}