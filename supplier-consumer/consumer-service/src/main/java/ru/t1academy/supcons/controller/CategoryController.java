package ru.t1academy.supcons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.t1academy.supcons.client.SupplierClient;
import ru.t1academy.supcons.dto.CategoryDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final SupplierClient supplierClient;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return supplierClient.getAllCategories();
    }
}
