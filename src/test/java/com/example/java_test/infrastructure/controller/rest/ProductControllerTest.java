package com.example.java_test.infrastructure.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_Electronics() throws Exception {
        mockMvc.perform(get("/api/products")
                        .param("category", "Electronics")
                        .param("sort", "price")
                        .param("sortDirection", "asc")
                        .param("page", "1")
                        .param("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.totalElements", is(10)));
    }

    @Test
    void test_Clothing() throws Exception {
        mockMvc.perform(get("/api/products")
                        .param("category", "Clothing")
                        .param("sort", "price")
                        .param("sortDirection", "asc")
                        .param("page", "1")
                        .param("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.totalElements", is(2)));
    }

    @Test
    void test_Accessories() throws Exception {
        mockMvc.perform(get("/api/products")
                        .param("category", "Accessories")
                        .param("sort", "price")
                        .param("sortDirection", "asc")
                        .param("page", "1")
                        .param("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.totalElements", is(3)));
    }

    @Test
    void test_no_sort() throws Exception {
        mockMvc.perform(get("/api/products")
                        .param("category", "Electronics")
                        .param("sortDirection", "asc")
                        .param("page", "1")
                        .param("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.totalElements", is(10)));
    }

    @Test
    void test_no_pagination() throws Exception {
        mockMvc.perform(get("/api/products")
                        .param("category", "Electronics")
                        .param("sort", "price")
                        .param("sortDirection", "asc")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.totalElements", is(10)));
    }

    @Test
    void test_no_category() throws Exception {
        mockMvc.perform(get("/api/products")
                        .param("sort", "price")
                        .param("sortDirection", "asc")
                        .param("page", "1")
                        .param("size", "10")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page", is(1)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.totalElements", is(30)));
    }
}