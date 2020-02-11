package com.beers.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.beers.api.request.BeerRequest;
import com.beers.api.response.BeerResponse;
import com.beers.api.service.BeerService;

@RestController
@RequestMapping("/beers")
public class BeerController {

	@Autowired
	private BeerService beerService;
	
	@GetMapping
	public ResponseEntity<List<BeerResponse>> listBeers() {
		return ResponseEntity.ok(beerService.getBeers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BeerResponse> listBeerById(
			@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(beerService.getBeerById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> createBeer(
		@RequestBody @Valid BeerRequest beerRequest,
		UriComponentsBuilder builder) throws Exception {
		BeerResponse newBeer = beerService.createNewBeer(beerRequest);
		return ResponseEntity.created(builder.path("/beers/{id}").buildAndExpand(newBeer.getId()).toUri()).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateBeer(
			@PathVariable Integer id,
			@RequestBody BeerRequest beerRequest) throws Exception {
		beerService.updateBeerById(id, beerRequest);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBeer(
			@PathVariable Integer id) throws Exception {
		beerService.deleteBeerById(id);
		return ResponseEntity.noContent().build();
	}
}
