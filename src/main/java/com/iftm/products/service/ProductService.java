package com.iftm.products.service;

import com.iftm.products.domain.product.Product;
import com.iftm.products.infra.database.ProductRepository;
import com.iftm.products.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(CreateProductRequest createProductRequest) {
        return productRepository.save(
                new Product(
                        createProductRequest.getName(),
                        createProductRequest.getPrice(),
                        createProductRequest.getDescription()
                )
        );
    }
}
