package io.spring.webflux.utils;

import org.springframework.beans.BeanUtils;

import io.spring.webflux.dto.ProductDto;
import io.spring.webflux.entity.Product;

public class Util {

	public static Product getEntityFromDto(ProductDto productDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return product;
	}

	public static ProductDto getDtoFromEntity(Product product) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(product, dto);
		return dto;
	}
}
