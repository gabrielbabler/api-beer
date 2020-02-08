package com.beers.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerResponse {
	private Integer id;
	private String name;
	private Double price;
	private String description;
}
