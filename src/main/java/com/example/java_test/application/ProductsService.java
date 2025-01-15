package com.example.java_test.application;

import com.example.java_test.domain.GetProductsData;
import com.example.java_test.domain.RetrieveProductsService;
import com.example.java_test.domain.model.Product;
import com.example.java_test.domain.model.ProductsQuery;
import com.example.java_test.infrastructure.controller.rest.dto.ResponseProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductsService implements RetrieveProductsService {

    @Autowired
    private GetProductsData repository;


    @Override
    public List<ResponseProduct> retrieveProducts(ProductsQuery query) {
        List<Product> products = repository.findProducts(query);

        return products.stream()
                .map(this::mapToResponseProduct)
                .toList();
    }

    @Override
    public long countProducts(String category) {
        return repository.countProductsByCategroy(category);
    }

    private ResponseProduct mapToResponseProduct(Product product) {
        float discount = calculateDiscount(product.sku(), product.category());
        float finalPrice = product.price() * (1 - discount);

        return new ResponseProduct(
                product.sku(),
                product.description(),
                product.category(),
                product.price(),
                discount,
                finalPrice
        );
    }

    private float calculateDiscount(String sku, String category) {
        if (sku.endsWith("5")) {
            return 0.30f;
        }

        return switch (category) {
            case "Home & Kitchen" -> 0.25f;
            case "Electronics" -> 0.15f;
            default -> 0.0f;
        };
    }
}
