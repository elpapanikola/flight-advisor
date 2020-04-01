package com.nikolanedeljkovic.flightadvisor.domain.airport;

import java.math.BigDecimal;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.nikolanedeljkovic.flightadvisor.domain.city.City;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

	@Id
	private Long Id;

	@Column
	private String name;
	
	@ManyToOne
	private City city; 
	
	@Column
	private String country;
	
	@Column
	private String IATA;
	
	@Column
	private String ICAO;
	
	@Column(precision = 10, scale = 6)
	private BigDecimal latitude;
	
	@Column(precision = 10, scale = 6)
	private BigDecimal longitude;
	
	@Column
	private Integer altitude;

	@Column
	private Float tzOffset;
	
	@Column
	private Character dst;
	
	@Column
	private TimeZone timeZone;
	
	@Column
	private String type;
	
	@Column
	private String informationSource;
	
}
