package com.example.products;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
//@Api(tags = "Product Operations", description = "Operations pertaining to Products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping("")
    //@ApiOperation(value = "Retrieve all product details ")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/new/product")
   // @ApiOperation(value = "Post product details when creating a new Product")
    public ResponseEntity<String> addNewProduct(@RequestBody ProductService.NewProduct newProduct) {
        ProductService.NewProduct newProduct1 = new ProductService.NewProduct(newProduct.name(),
                newProduct.description(),
                newProduct.price());
        productService.addNewProduct(newProduct1);

        return ResponseEntity.ok("Product added successfully");
    }

    record UpdateProduct(int id,String name,String description,double price){
    }
    @PutMapping("/edit/{id}")
   // @ApiOperation(value = "Update  product details with product id ")
    public ResponseEntity<String> updateProduct(@PathVariable("id") int id, @RequestBody UpdateProduct updateProduct){
        productService.updateProduct(id,
                updateProduct.name(),
                updateProduct.description(),
                updateProduct.price());

        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{id}")
   // @ApiOperation(value = "Delete a product's  details using product Id ")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully.");

    }

}
