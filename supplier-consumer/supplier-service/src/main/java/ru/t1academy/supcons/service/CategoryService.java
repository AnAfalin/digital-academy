package ru.t1academy.supcons.service;

import org.springframework.http.ResponseEntity;
import ru.t1academy.supcons.dto.CategoryDto;
import ru.t1academy.supcons.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    ResponseEntity<?> createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long categoryId);

    ResponseEntity<?> updateCategoryById(Long categoryId, CategoryDto categoryDto);

    ResponseEntity<?> deleteCategoryById(Long categoryId);

    Optional<Category> findCategory(CategoryDto category);
}
