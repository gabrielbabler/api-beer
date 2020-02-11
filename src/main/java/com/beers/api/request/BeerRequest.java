package com.beers.api.request;

import javax.validation.constraints.NotBlank;

import com.beers.api.domain.BeerDomain;
import com.sun.istack.internal.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerRequest {
	@NotBlank
	private String name;
	@NotNull
	private Double price;
	@NotBlank
	private String description;
	
	public BeerDomain convertToDomain() {
		return BeerDomain.builder()
				.name(name)
				.price(price)
				.description(description)
				.build();
	}
}
