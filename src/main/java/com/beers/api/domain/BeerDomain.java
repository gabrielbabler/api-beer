package com.beers.api.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Beers")
public class BeerDomain {
	private Integer id;
	private String name;
	private Double price;
	private String description;
}
