package com.nikolanedeljkovic.flightadvisor.domain.city;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.nikolanedeljkovic.flightadvisor.domain.airport.Airport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String country;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy = "city")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "city")
	private List<Airport> airports;
}
