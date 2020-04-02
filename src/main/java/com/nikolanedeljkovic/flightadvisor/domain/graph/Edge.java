package com.nikolanedeljkovic.flightadvisor.domain.graph;

import org.jgrapht.graph.DefaultWeightedEdge;


public class Edge extends DefaultWeightedEdge {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1334160540198866409L;
	private String airline;
	
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
}
