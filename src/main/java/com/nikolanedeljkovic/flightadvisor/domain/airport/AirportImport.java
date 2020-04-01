package com.nikolanedeljkovic.flightadvisor.domain.airport;

import com.opencsv.bean.CsvBindByPosition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirportImport {

	@CsvBindByPosition(position = 0)
	private String Id;
	
	@CsvBindByPosition(position = 1)
	private String name;
	
	@CsvBindByPosition(position = 2)
	private String city; 
	
	@CsvBindByPosition(position = 3)
	private String country;
	
	@CsvBindByPosition(position = 4)
	private String IATA;
	
	@CsvBindByPosition(position = 5)
	private String ICAO;
	
	@CsvBindByPosition(position = 6)
	private String latitude;
	
	@CsvBindByPosition(position = 7)
	private String longitude;
	
	@CsvBindByPosition(position = 8)
	private String altitude;

	@CsvBindByPosition(position = 9)
	private String tzOffset;
	
	@CsvBindByPosition(position = 10)
	private String dst;
	
	@CsvBindByPosition(position = 11)
	private String timeZone;
	
	@CsvBindByPosition(position = 12)
	private String type;
	
	@CsvBindByPosition(position = 13)
	private String informationSource;
}
