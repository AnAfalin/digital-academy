package ru.t1academy.supcons.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.t1academy.supcons.dto.CategoryDto;
import ru.t1academy.supcons.model.Category;
import ru.t1academy.supcons.repository.CategoryRepository;
import ru.t1academy.supcons.service.CategoryService;
import ru.t1academy.supcons.service.mapper.CategoryMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    public ResponseEntity<?> createCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.toCategory(categoryDto));
        return new ResponseEntity<>("Category was successful saved", HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryDtos(categories);
    }

    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = getCategoryIfExists(categoryId);

        return categoryMapper.toCategoryDto(category);
    }

    @Transactional
    public ResponseEntity<?> updateCategoryById(Long categoryId, CategoryDto categoryDto) {
        Category foundCategory = getCategoryIfExists(categoryId);
        updateCategory(foundCategory, categoryDto);

        categoryRepository.save(foundCategory);

        return new ResponseEntity<>("Category was successful updated", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);

        return new ResponseEntity<>("Category was successful deleted", HttpStatus.OK);
    }

    private Category getCategoryIfExists(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category with id='%s' not found".formatted(categoryId)));
    }

    private void updateCategory(Category updatableCategory, CategoryDto srcCategory) {
        if (StringUtils.hasLength(srcCategory.getName())) {
            updatableCategory.setName(srcCategory.getName());
        }
    }

    @Transactional
    public Optional<Category> findCategory(CategoryDto category) {
        return Objects.nonNull(category.getId())
                ? categoryRepository.findById(category.getId())
                : categoryRepository.findByName(category.getName());
    }
}
