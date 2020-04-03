package com.nikolanedeljkovic.flightadvisor.domain.city;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NamedNativeQuery;

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
@NamedNativeQuery(
		name = City.GET_ALL_CITIES_WITH_LAST_X_COMMENTS,
		query = "SELECT ct.* ,cm.* FROM Comment as cm " + 
				"LEFT JOIN Comment  as cm2 " + 
				"ON cm.city_id = cm2.city_id AND cm.created_at <= cm2.created_at left join City ct on ct.id = cm.city_id " + 
				"GROUP BY cm.id " + 
				"HAVING COUNT(*) <= :numberOfComments " + 
				"ORDER BY cm.id, cm.created_at desc",
		resultClass = City.class
)
public class City {
	
	public static final String GET_ALL_CITIES_WITH_LAST_X_COMMENTS = "getAllCitiesWithLastXComments";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column
	private String name;
	
	@NotBlank
	@Column
	private String country;
	
	@NotBlank
	@Column
	private String description;
	
	@OneToMany(mappedBy = "city")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "city")
	private List<Airport> airports;
}
