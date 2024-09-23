package com.restapi.products.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restapi.products.dto.ProductDto;
import com.restapi.products.entities.Product;
import com.restapi.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@Log4j2
@Slf4j
@Tag(name = "Product Operations", description = "Product Management APIS")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping("")
    @Operation(summary = "Retrieve all product details ")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve product details by Id")
    public ResponseEntity<List<ProductDto>> getProductDetails(@PathVariable("id") Long id){
        var data = productService.findProductById(id);
         return ResponseEntity.ok().body(data);

    }

    @PostMapping("")
    @Operation(summary = "Add/Create a new product")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) throws JsonProcessingException {

        productService.addNewProduct(productDto);

        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update  product details  ")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) throws JsonProcessingException {

        productDto.setProductId(id);
        productService.updateProduct(productDto);

        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product's  details using product Id ")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();

    }

}
