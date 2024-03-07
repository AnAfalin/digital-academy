package ru.t1academy.supcons.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.t1academy.supcons.dto.FilterProductRequest;
import ru.t1academy.supcons.dto.ProductDto;

import java.util.List;


public interface ProductService {
    ResponseEntity<?> createProduct(ProductDto productDto);

    void updateProductById(Long productId, ProductDto product);

    void deleteProductById(Long productId);

    ProductDto getProductById(Long productId);

    List<ProductDto> getAllProducts(FilterProductRequest request, Pageable pageable);

}
