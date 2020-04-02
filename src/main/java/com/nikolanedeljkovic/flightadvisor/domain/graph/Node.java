package com.nikolanedeljkovic.flightadvisor.domain.graph;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Node {
	Long airportId;
	String iata;
}
