package com.beers.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beers.api.domain.BeerDomain;

@Repository
public interface BeerRepository extends JpaRepository<BeerDomain, String>{
	public Optional<BeerDomain> findByName(String name);
}