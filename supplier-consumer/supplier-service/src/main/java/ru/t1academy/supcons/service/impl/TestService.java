package ru.t1academy.supcons.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1academy.supcons.model.Category;
import ru.t1academy.supcons.model.Product;
import ru.t1academy.supcons.repository.CategoryRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TestService {
    private final CategoryRepository categoryRepository;

    @PostConstruct
    private void init() {
        Product product1 = Product.builder()
                .name("Intel Core i9-13900K")
                .description("Intel 13-го поколения Core™ i9\n" +
                        "разблокированный настольный процессор ")
                .price(52000.00)
                .build();
        Product product2 = Product.builder()
                .name("Intel Core i7-12700KF")
                .description("Процессор Intel Core™ i7-12700KF 12-го поколения для настольных ПК")
                .price(25000.00)
                .build();
        Product product3 = Product.builder()
                .name("ASUS Dual GeForce RTX 4070")
                .description("Видеокарта ASUS Dual GeForce RTX 4070 SUPER OC Edition (PCIe 4.0, 12 ГБ GDDR6X, DLSS 3, " +
                        "HDMI 2.1, DisplayPort 1.4a, конструкция с 2,56 слотами, конструкция вентилятора Axial-tech, " +
                        "Auto-Extreme Tech) DUAL-RTX4070S-O12G ")
                .price(60000.00)
                .build();
        Category category1 = Category.builder()
                .name("Processors")
                .build();
        Category category2 = Category.builder()
                .name("Video Graphic Devices")
                .build();

        category1.setProducts(List.of(product1, product2));
        category2.setProducts(List.of(product3));

        categoryRepository.saveAll(List.of(category1, category2));
    }
}
