package com.beers.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.beers.api.domain.BeerDomain;
import com.beers.api.repository.BeerRepository;
import com.beers.api.request.BeerRequest;
import com.beers.api.response.BeerResponse;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;
	
	/**
	 * Método responsável por fazer uma chamada no repositório (banco de dados) e devolver todas as
	 * informações das cervejas cadastradas.
	 * @return Lista no formato de BeerResponse
	 */
	public List<BeerResponse> getBeers() {
		return beerRepository.findAll()
				.stream()				
				.map(BeerDomain::convertToResponse) 
				.collect(Collectors.toList()); 
	}
	
	/**
	 * Método responsável por retornar dados de uma cerveja
	 * @param id identificador da cerveja
	 * @return BeerResponse
	 * @throws Exception caso não exista o id informado na base de dados
	 */
	public BeerResponse getBeerById(Integer id) throws Exception {
		return beerRepository.findById(id.toString()) 
				.map(BeerDomain::convertToResponse)
				.orElseThrow(Exception::new);
	}
	
	/**
	 * Método responsável por cadastrar uma nova cerveja na base de dados
	 * @param beerRequest body de requisição com os dados da cerveja
	 * @throws Exception caso já exista uma cerveja com este nome
	 */
	public BeerResponse createNewBeer(BeerRequest beerRequest) throws Exception {
		if (!beerRepository.findByName(beerRequest.getName()).isPresent()) {
			beerRepository.save(beerRequest.convertToDomain());
		}
		throw new Exception();
	}
	
	/**
	 * Método responsável por atualizar dados de uma cerveja já existente
	 * @param id identificador da cerveja existente que será atualizada
	 * @param beerRequest dados que serão alterados da cerveja
	 * @throws Exception é lançado caso não exista na base de dados
	 */
	public void updateBeerById(Integer id, BeerRequest beerRequest) throws Exception {
		Optional<BeerDomain> domain = beerRepository.findById(id.toString());
		if (!domain.isPresent()) {
			throw new Exception();
		}
		beerRepository.save(buildNewBeerDomain(domain.get(), beerRequest));
	}
	
	/**
	 * Método responsável por deletar uma cerveja da base de dados
	 * @param id identificador da cerveja 
	 * @throws Exception é lançado caso o id não exista na base de dados
	 */
	public void deleteBeerById(Integer id) throws Exception {
		if (!beerRepository.findById(id.toString()).isPresent()) {
			throw new Exception();
		}
		beerRepository.deleteById(id.toString());
	}
	
	/**
	 * Método responsável por validar os campos que foram passados para atualização
	 * @param id identificador da cerveja
	 * @param beerRequest campos que serão atualizados
	 * @return BeerDomain com os valores atualizados
	 * @throws Exception caso seja atualizado o nome e este nome já exista na base de dados
	 */
	private BeerDomain buildNewBeerDomain(BeerDomain domain, BeerRequest beerRequest) throws Exception {
		if (beerRepository.findByName(beerRequest.getName()).isPresent()) { 
			throw new Exception();
		}
		domain.setName(beerRequest.getName());
		
		if (beerRequest.getPrice() != null) {
			domain.setPrice(beerRequest.getPrice());
		}
		if (!StringUtils.isEmpty(beerRequest.getDescription())) {
			domain.setDescription(beerRequest.getDescription());
		}
		return domain;
	}
}
