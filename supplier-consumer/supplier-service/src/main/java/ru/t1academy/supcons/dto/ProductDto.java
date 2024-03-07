package ru.t1academy.supcons.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private Double price;

    private CategoryDto category;
}
