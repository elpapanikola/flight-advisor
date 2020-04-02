package com.nikolanedeljkovic.flightadvisor.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PathDTO {
	Double price;
	List<RouteDTO> routes;
}
