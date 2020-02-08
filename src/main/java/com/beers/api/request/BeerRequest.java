package com.beers.api.request;

import com.beers.api.domain.BeerDomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerRequest {
	private String name;
	private Double price;
	private String description;
	
	public BeerDomain convertToDomain() {
		return BeerDomain.builder()
				.name(name)
				.price(price)
				.description(description)
				.build();
	}
}
