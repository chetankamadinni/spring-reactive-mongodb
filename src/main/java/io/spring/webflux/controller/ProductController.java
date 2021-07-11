package io.spring.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.spring.webflux.dto.ProductDto;
import io.spring.webflux.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public Flux<ProductDto> getAllProducts() {
		return productService.getProducts();
	}

	@GetMapping("/{id}")
	public Mono<ProductDto> getProduct(@PathVariable String id) {
		return productService.getProduct(id);
	}

	@GetMapping("/range")
	public Flux<ProductDto> getProductsByRange(@RequestParam double min, @RequestParam double max) {
		return productService.getProdutsinRange(min, max);
	}

	@PostMapping
	public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> mono) {
		return productService.createProduct(mono);
	}

	@PutMapping("/{id}")
	public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> mono, @PathVariable String id) {
		return productService.updateProduct(mono, id);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteProduct(@PathVariable String id) {
		return productService.deleteProduct(id);
	}

}
