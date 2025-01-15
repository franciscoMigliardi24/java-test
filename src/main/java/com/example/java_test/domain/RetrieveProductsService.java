package com.example.java_test.domain;

import com.example.java_test.domain.model.ProductsQuery;
import com.example.java_test.infrastructure.controller.rest.dto.ResponseProduct;

import java.util.List;

public interface RetrieveProductsService {

    List<ResponseProduct> retrieveProducts(ProductsQuery query);

    long countProducts(String category);
}
