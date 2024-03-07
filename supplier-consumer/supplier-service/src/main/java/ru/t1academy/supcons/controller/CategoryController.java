package ru.t1academy.supcons.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1academy.supcons.dto.CategoryDto;
import ru.t1academy.supcons.service.impl.CategoryServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto category) {
        log.info("Request for save new category {}", category);
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        log.info("Request for get all categories");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        log.info("Request for get category with id={}", id);
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Long id, @RequestBody CategoryDto category) {
        log.info("Request for update category with id={}, new version product={}", id, category);
        return categoryService.updateCategoryById(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
        log.info("Request for delete category with id={}", id);
        return categoryService.deleteCategoryById(id);
    }
}
