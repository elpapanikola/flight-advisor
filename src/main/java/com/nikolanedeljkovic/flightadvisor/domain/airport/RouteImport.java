package com.nikolanedeljkovic.flightadvisor.domain.airport;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteImport {
	
	@CsvBindByPosition(position = 0)
	private String airline;
	
	@CsvBindByPosition(position = 1)
	private String airlineId;
	
	@CsvBindByPosition(position = 2)
	private String sourceAirport;
	
	@CsvBindByPosition(position = 3)
	private String sourceAirportId;
	 
	@CsvBindByPosition(position = 4)
	private String destinationAirport;
	
	@CsvBindByPosition(position = 5)
	private String destinationAirportId;
	
	@CsvBindByPosition(position = 6)
	private String codeShare;

	@CsvBindByPosition(position = 7)
	private String stops;
	
	@CsvBindByPosition(position = 8)
	private String equipment;
	
	@CsvBindByPosition(position = 9)
	private String price;
}
