package ru.t1academy.supcons.service.mapper;

import org.mapstruct.Mapper;
import ru.t1academy.supcons.dto.ProductDto;
import ru.t1academy.supcons.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductDto dto);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtos(List<Product> products);
}
