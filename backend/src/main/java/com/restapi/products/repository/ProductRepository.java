package com.restapi.products.repository;

import com.restapi.products.dto.ProductDto;
import com.restapi.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.restapi.products.dto.ProductDto(p.id, p.name, p.description, p.price) " +
            "FROM Product p WHERE p.id = :id")
    List<ProductDto> findProductById(@Param("id") Long id);

}
