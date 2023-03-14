package com.example.products;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    record NewProduct (  String name, String description, double price){

    }
    public void  addNewProduct(NewProduct newProduct){
        Product product = new Product();
        product.setDescription(newProduct.description);
        product.setName(newProduct.name);
        product.setPrice(newProduct.price);

        productRepository.save(product);
    }
    @Transactional
    public void updateProduct(int id, String name, String description, double price) {

        Product product=productRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Product with id "+id+" does not exist"));
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
            //productRepository.save(product);
        }

    public void deleteProduct(int id) {
            boolean exists = productRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Product with id " + id +"does not exist");
        }
        productRepository.deleteById(id);
    }


}
