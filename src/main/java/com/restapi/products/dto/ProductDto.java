package com.restapi.products.dto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Product Dto",description = "Dto for handling product details")
public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
}
