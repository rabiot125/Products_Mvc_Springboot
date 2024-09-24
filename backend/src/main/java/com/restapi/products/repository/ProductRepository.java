package com.restapi.products.repository;

import com.restapi.products.dto.ProductDto;
import com.restapi.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductDto> findProductById(Long id);

}
