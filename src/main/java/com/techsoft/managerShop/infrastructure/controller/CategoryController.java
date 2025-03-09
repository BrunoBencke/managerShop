package com.techsoft.managerShop.infrastructure.controller;

import com.techsoft.managerShop.application.dto.CategoryDTO;
import com.techsoft.managerShop.domain.model.Category;
import com.techsoft.managerShop.domain.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Endpoints for managing product categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieves a list of all registered categories.")
    @ApiResponse(responseCode = "200", description = "List of categories retrieved successfully")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Retrieves a specific category based on its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category found"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Category> getCategoryById(
            @Parameter(description = "ID of the category to be retrieved") @PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Adds a new category to the database.")
    @ApiResponse(responseCode = "201", description = "Category successfully created")
    public ResponseEntity<Category> createCategory(
            @Parameter(description = "Category data to be created") @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Updates an existing category based on its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category successfully updated"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Category> updateCategory(
            @Parameter(description = "ID of the category to be updated") @PathVariable Long id,
            @Parameter(description = "Updated category data") @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Removes a category based on its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Category successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "ID of the category to be deleted") @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
