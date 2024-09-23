package com.restapi.products.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restapi.products.dto.ProductDto;
import com.restapi.products.entities.Product;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<ProductDto> findProductById(Long id);
    void addNewProduct(ProductDto productDto) throws JsonProcessingException;
    @Transactional
    void updateProduct(ProductDto productDto) throws JsonProcessingException;
    void deleteProduct(Long id);
}
