package com.example.java_test.infrastructure.repositories.h2;

import com.example.java_test.domain.GetProductsData;
import com.example.java_test.domain.model.Product;
import com.example.java_test.domain.model.ProductsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class JdbcGetProductsData implements GetProductsData {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public long countProductsByCategroy(String category) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT COUNT(*) FROM PRODUCTS";

        if (category != null) {
            sql += " WHERE category = :category";
            params.put("category", category);
        }

        return namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
    }

    @Override
    public List<Product> findProducts(ProductsQuery query) {
        String direction = "ASC";
        if ("desc".equalsIgnoreCase(query.sortDirection())) {
            direction = "DESC";
        }

        String sql = ""
                + "SELECT sku, price, description, category "
                + "FROM products "
                + "WHERE (:category IS NULL OR category = :category) "
                + "ORDER BY "
                + "  CASE "
                + "    WHEN :orderBy = 'SKU' THEN sku "
                + "    WHEN :orderBy = 'Description' THEN description "
                + "    WHEN :orderBy = 'Category' THEN category "
                + "    ELSE '' "
                + "  END " + direction + ", "
                + "  CASE "
                + "    WHEN :orderBy = 'Price' THEN price "
                + "    ELSE NULL "
                + "  END " + direction + " "
                + "OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY";

        Map<String, Object> params = new HashMap<>();
        params.put("category", query.category());
        params.put("orderBy", query.sort());
        params.put("offset", (query.page() - 1) * query.size());
        params.put("limit", query.size());

        // 5) Ejecutamos la consulta con un RowMapper que mapee al objeto Product
        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Product products = new Product(
                    rs.getString("sku"),
                    rs.getFloat("price"),
                    rs.getString("description"),
                    rs.getString("category"));
            return products;
        });
    }
}

