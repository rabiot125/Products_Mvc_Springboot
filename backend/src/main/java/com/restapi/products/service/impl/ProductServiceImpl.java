package com.restapi.products.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.products.dto.ProductDto;
import com.restapi.products.entities.Product;
import com.restapi.products.exceptions.errors.ResourceNotFoundException;
import com.restapi.products.repository.ProductRepository;
import com.restapi.products.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public ProductServiceImpl(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDto> findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Product with id " + id + " does not exist "));
        log.info("Fetching Product details {} :", +id);
        return productRepository.findProductById(id);
    }

    @Override
    public void addNewProduct(ProductDto productDto) throws JsonProcessingException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        log.info("Product Object {}", objectMapper.writeValueAsString(product));

        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(ProductDto productDto) throws JsonProcessingException {
        Long id = productDto.getProductId();
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id " + id + " does not exist"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        log.info("Updating Product details {} :", objectMapper.writeValueAsString(productDto));

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Product with id " + id + "does not exist");
        }
        productRepository.deleteById(id);
    }

}
