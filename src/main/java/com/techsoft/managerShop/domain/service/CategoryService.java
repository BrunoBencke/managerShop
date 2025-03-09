package com.techsoft.managerShop.domain.service;

import com.techsoft.managerShop.application.dto.CategoryDTO;
import com.techsoft.managerShop.domain.model.Category;
import com.techsoft.managerShop.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        if (categoryDTO.getCategoryDefaultId() != null) {
            categoryRepository.findById(categoryDTO.getCategoryDefaultId()).ifPresent(category::setCategoryDefault);
        }

        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, CategoryDTO categoryDTO) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());

            if (categoryDTO.getCategoryDefaultId() != null) {
                categoryRepository.findById(categoryDTO.getCategoryDefaultId()).ifPresent(category::setCategoryDefault);
            }

            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
