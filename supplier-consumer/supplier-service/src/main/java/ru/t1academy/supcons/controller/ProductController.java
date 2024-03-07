package ru.t1academy.supcons.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1academy.supcons.dto.FilterProductRequest;
import ru.t1academy.supcons.dto.ProductDto;
import ru.t1academy.supcons.service.impl.ProductServiceImpl;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDto product) {
        log.info("Request for save new product {}", product);
        return productService.createProduct(product);
    }

    @GetMapping
    public List<ProductDto> getProducts(@ModelAttribute FilterProductRequest request,
                                        @RequestParam(defaultValue = "0") Integer from,
                                        @RequestParam(defaultValue = "10") Integer size) {
        log.info("Request for get all products with filter by: {}", request);
        return productService.getAllProducts(request, PageRequest.of(from / size, size));
    }


    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        log.info("Request for get product with id={}", id);
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable Long id, @RequestBody ProductDto product) {
        log.info("Request for update product with id={}, new version product={}", id, product);
        productService.updateProductById(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        log.info("Request for delete product with id={}", id);
        productService.deleteProductById(id);
    }

}
