package com.example.java_test.infrastructure.controller.rest;

import com.example.java_test.domain.RetrieveProductsService;
import com.example.java_test.domain.model.Category;
import com.example.java_test.domain.model.Paginator;
import com.example.java_test.domain.model.ProductsQuery;
import com.example.java_test.generated.infrastructure.controllers.rest.GetProductsListApi;
import com.example.java_test.generated.infrastructure.controllers.rest.model.ProductDetailsDto;
import com.example.java_test.generated.infrastructure.controllers.rest.model.ProductsListResponseDto;
import com.example.java_test.infrastructure.controller.rest.dto.ResponseProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController implements GetProductsListApi {

    @Autowired
    private RetrieveProductsService productService;

    @Override
    public ResponseEntity<ProductsListResponseDto> getProductsList(String category, String sort, String sortDirection, Integer page, Integer size) {
        Paginator paginator = new Paginator(page, size, sort, sortDirection);
        ProductsQuery query = new ProductsQuery(paginator, new Category(category));
        List<ResponseProduct> products = productService.retrieveProducts(query);
        long totalElements = productService.countProducts(category);
        ProductsListResponseDto productsListResponseDto = getProductsListResponseDto(page, size, (int) totalElements, products);

        return ResponseEntity.ok(productsListResponseDto);
    }

    private static ProductsListResponseDto getProductsListResponseDto(Integer page, Integer size, int totalElements, List<ResponseProduct> products) {
        ProductsListResponseDto productsListResponseDto = new ProductsListResponseDto();
        productsListResponseDto.setPage(page);
        productsListResponseDto.setSize(size);
        productsListResponseDto.setTotalElements(totalElements);
        productsListResponseDto.setProducts(products.stream().map(product -> new ProductDetailsDto()
                .sku(product.sku())
                .description(product.description())
                .category(product.category())
                .originalPrice(product.originalPrice())
                .discountApplied(product.discountApplied())
                .finalPrice(product.finalPrice())
        ).toList());
        return productsListResponseDto;
    }
}
