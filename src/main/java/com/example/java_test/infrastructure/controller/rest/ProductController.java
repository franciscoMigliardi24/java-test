package com.example.java_test.infrastructure.controller.rest;

import com.example.java_test.domain.RetrieveProductsService;
import com.example.java_test.domain.model.ProductsQuery;
import com.example.java_test.infrastructure.controller.rest.dto.ProductResponse;
import com.example.java_test.infrastructure.controller.rest.dto.ResponseProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private RetrieveProductsService productService;

    @GetMapping
    public ResponseEntity<ProductResponse> getProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "sort", required = false, defaultValue = "sku") String sort,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "asc") String sortDirection,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {

        // Ejemplo de lógica (ajusta a tu implementación real):
        ProductsQuery query = new ProductsQuery(page, size, sort, category, sortDirection);
        List<ResponseProduct> products = productService.retrieveProducts(query);
        long totalElements = productService.countProducts(category);

        ProductResponse response = new ProductResponse(page, size, totalElements, products);
        return ResponseEntity.ok(response);
    }
}
