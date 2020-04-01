package com.nikolanedeljkovic.flightadvisor.domain.airport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	private String airline;
	
	@Column
	private String airlineId;
	
	@Column
	private String sourceAirport;
	
	@Column
	private Long sourceAirportId;
	 
	@Column
	private String destinationAirport;
	
	@Column
	private Long destinationAirportId;
	
	@Column
	private Character codeShare;

	@Column
	private Integer stops;
	
	@Column
	private String equipment;
	
	@Column
	private Float price;
}
