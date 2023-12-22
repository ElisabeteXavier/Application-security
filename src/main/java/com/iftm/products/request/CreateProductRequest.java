package com.iftm.products.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private String name;
    private Double price;
    private String description;
}
