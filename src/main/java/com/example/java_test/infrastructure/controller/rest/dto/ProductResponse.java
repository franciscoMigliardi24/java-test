package com.example.java_test.infrastructure.controller.rest.dto;

import java.util.List;

public record ProductResponse(int page, int size, long totalElements, List<ResponseProduct> products) {
}
