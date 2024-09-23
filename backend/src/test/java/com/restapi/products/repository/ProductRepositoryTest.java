package com.restapi.products.repository;

import com.restapi.products.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private  ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1L,"Test","Testing",new BigDecimal(400));
        productRepository.save(product);

    }

    @AfterEach
    void tearDown() {

    }
}
