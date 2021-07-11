package io.spring.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.webflux.dto.ProductDto;
import io.spring.webflux.repository.ProductRepository;
import io.spring.webflux.utils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public Flux<ProductDto> getProducts() {
		return repository.findAll().map(Util::getDtoFromEntity);
	}

	public Mono<ProductDto> getProduct(String id) {
		return repository.findById(id).map(Util::getDtoFromEntity);
	}

	public Flux<ProductDto> getProdutsinRange(double min, double max) {
		return repository.findByPriceBetween(min, max);
	}

	public Mono<ProductDto> createProduct(Mono<ProductDto> productDto) {
		return productDto.map(Util::getEntityFromDto).flatMap(repository::insert).map(Util::getDtoFromEntity);
	}

	public Mono<ProductDto> updateProduct(Mono<ProductDto> prodMono, String id) {
		return repository.findById(id).flatMap(p -> prodMono.map(Util::getEntityFromDto).doOnNext(e -> e.setId(id)))
				.flatMap(repository::save).map(Util::getDtoFromEntity);
	}

	public Mono<Void> deleteProduct(String id) {
		return repository.deleteById(id);
	}

}
