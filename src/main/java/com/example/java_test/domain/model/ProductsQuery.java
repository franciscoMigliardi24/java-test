package com.example.java_test.domain.model;

public record ProductsQuery(int page, int size, String sort, String category, String sortDirection) {
}
