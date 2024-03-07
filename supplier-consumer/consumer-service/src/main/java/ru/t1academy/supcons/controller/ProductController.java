package ru.t1academy.supcons.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1academy.supcons.client.SupplierClient;
import ru.t1academy.supcons.dto.FilterProductRequest;
import ru.t1academy.supcons.dto.ProductDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final SupplierClient supplierClient;

    @GetMapping
    public List<ProductDto> getAllProducts(@ModelAttribute FilterProductRequest request,
                                           @RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(defaultValue = "10") Integer size) {
        return supplierClient.getAllProducts(request, from, size);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        supplierClient.deleteProductById(id);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto product) {
        return supplierClient.addProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProductById(@PathVariable Long id, @RequestBody ProductDto product) {
        supplierClient.updateProductById(id, product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return supplierClient.getProductById(id);
    }
}
