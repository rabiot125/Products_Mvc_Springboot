package com.restapi.products.repository;

import com.restapi.products.dto.ProductDto;
import com.restapi.products.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    private Product product;
    @BeforeEach
    void setUp() {
        product = new Product(1L, "Test", "Testing", new BigDecimal(400));
        productRepository.save(product);

    }
    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }
    @Test
    void testFindProductById() {
        List<ProductDto>  foundProduct = productRepository.findProductById(1L);
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.get(0).getProductId()).isEqualTo(product.getId());
        assertThat(foundProduct.get(0).getName()).isEqualTo(product.getName());

    }
    @Test
    void testFindProductByIdNotFound(){
        //ProductDto productDto= new ProductDto(1L, "Test", "Testing", new BigDecimal(400));
        List<ProductDto> product1= productRepository.findProductById(3L);
        assertThat(product1.isEmpty()).isTrue();
    }

}
