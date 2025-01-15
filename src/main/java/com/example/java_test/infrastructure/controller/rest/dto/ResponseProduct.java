package com.example.java_test.infrastructure.controller.rest.dto;

public record ResponseProduct(String sku, String description, String category, float originalPrice, float discountApplied, float finalPrice) {
}