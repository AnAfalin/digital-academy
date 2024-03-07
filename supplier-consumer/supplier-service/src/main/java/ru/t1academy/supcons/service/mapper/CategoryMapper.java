package ru.t1academy.supcons.service.mapper;

import org.mapstruct.Mapper;
import ru.t1academy.supcons.dto.CategoryDto;
import ru.t1academy.supcons.model.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto dto);
    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toCategoryDtos(List<Category> categories);
}
