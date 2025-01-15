package com.example.java_test.domain;

import com.example.java_test.domain.model.Product;
import com.example.java_test.domain.model.ProductsQuery;

import java.util.List;

public interface GetProductsData {

    long countProductsByCategroy(String category);

    List<Product> findProducts(ProductsQuery query);
}
