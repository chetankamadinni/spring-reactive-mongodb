package io.spring.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.spring.webflux.dto.ProductDto;
import io.spring.webflux.entity.Product;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

	Flux<ProductDto> findByPriceBetween(double min, double max);

}
