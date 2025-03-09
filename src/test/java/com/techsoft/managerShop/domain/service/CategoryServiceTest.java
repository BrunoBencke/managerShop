package com.techsoft.managerShop.domain.service;

import com.techsoft.managerShop.application.dto.CategoryDTO;
import com.techsoft.managerShop.domain.model.Category;
import com.techsoft.managerShop.domain.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category(1L, "Electronics", "Category for electronic devices", null);
    }

    @Test
    void shouldReturnAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<Category> categories = categoryService.getAllCategories();

        assertEquals(1, categories.size());
        assertEquals("Electronics", categories.getFirst().getName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> foundCategory = categoryService.getCategoryById(1L);

        assertTrue(foundCategory.isPresent());
        assertEquals("Electronics", foundCategory.get().getName());
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void shouldCreateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Books");
        categoryDTO.setDescription("Category for books");

        Category newCategory = new Category(null, "Books", "Category for books", null);
        when(categoryRepository.save(any(Category.class))).thenReturn(newCategory);

        Category savedCategory = categoryService.createCategory(categoryDTO);

        assertNotNull(savedCategory);
        assertEquals("Books", savedCategory.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void shouldUpdateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Updated Electronics");
        categoryDTO.setDescription("Updated description");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category updatedCategory = categoryService.updateCategory(1L, categoryDTO);

        assertEquals("Updated Electronics", updatedCategory.getName());
        assertEquals("Updated description", updatedCategory.getDescription());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void shouldDeleteCategory() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
