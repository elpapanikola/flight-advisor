package com.nikolanedeljkovic.flightadvisor.domain.airport;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Route {
	
	@Id
	private Long Id;
	
	@Column
	private String airlineId;
	
	@ManyToOne
	private Airport sourceAirport;
	 
}
