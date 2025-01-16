package com.example.java_test.application;

import com.example.java_test.domain.GetProductsData;
import com.example.java_test.domain.model.Product;
import com.example.java_test.domain.model.ProductsQuery;
import com.example.java_test.infrastructure.controller.rest.dto.ResponseProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductsServiceTest {

    @Mock
    private GetProductsData repository;

    @InjectMocks
    private ProductsService productsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveProducts() {
        Product product = new Product("12345", 100.0f, "Test Product", "Electronics");
        when(repository.findProducts(any(ProductsQuery.class))).thenReturn(List.of(product));

        List<ResponseProduct> responseProducts = productsService.retrieveProducts(new ProductsQuery(null, null));

        assertEquals(1, responseProducts.size());
        assertEquals("12345", responseProducts.getFirst().sku());
        assertEquals(100.0f, responseProducts.getFirst().originalPrice());
        assertEquals("Test Product", responseProducts.getFirst().description());
        assertEquals("Electronics", responseProducts.getFirst().category());
        assertEquals(0.3f, responseProducts.getFirst().discountApplied());
        assertEquals(70.0f, responseProducts.getFirst().finalPrice());
    }

    @Test
    void countProducts() {
        when(repository.countProductsByCategroy("Electronics")).thenReturn(10L);

        long count = productsService.countProducts("Electronics");

        assertEquals(10L, count);
    }
}