package com.techsoft.managerShop.infrastructure.controller;

import com.techsoft.managerShop.domain.model.Category;
import com.techsoft.managerShop.domain.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.config.name=application-test")
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
        category = categoryRepository.save(new Category(null, "Furniture", "Category for furniture", null));
    }

    @Test
    void shouldGetAllCategories() throws Exception {
        mockMvc.perform(get("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].name", is("Furniture")));
    }

    @Test
    void shouldGetCategoryById() throws Exception {
        mockMvc.perform(get("/api/categories/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Furniture")));
    }

    @Test
    void shouldReturnNotFoundForInvalidCategoryId() throws Exception {
        mockMvc.perform(get("/api/categories/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateCategory() throws Exception {
        String jsonRequest = """
            {
                "name": "Appliances",
                "description": "Category for appliances"
            }
        """;

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Appliances")));
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        String jsonRequest = """
            {
                "name": "Updated Furniture",
                "description": "Updated description"
            }
        """;

        mockMvc.perform(put("/api/categories/{id}", category.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Furniture")));
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        mockMvc.perform(delete("/api/categories/{id}", category.getId()))
                .andExpect(status().isNoContent());
    }
}