package com.restapi.products.repository;

import com.restapi.products.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private  ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }
}
