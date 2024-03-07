package ru.t1academy.supcons.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "Description cannot be null and must contain at least one non-whitespace character")
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "50.00", message = "Price must be more than 50.00")
    private Double price;

    private CategoryDto category;
}
