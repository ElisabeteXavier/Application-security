package com.iftm.products.controller;

import com.iftm.products.request.CreateProductRequest;
import com.iftm.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @Operation(summary = "Cadastrar produto", security = @SecurityRequirement(name = "JWT"))
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.status(201).body(productService.createProduct(request));
    }

}
