package com.example.java_test.infrastructure.repositories.h2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Products {

    @Id
    private String sku;
    private Float price;
    private String description;
    private String category;
}