package ru.t1academy.supcons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterProductRequest {
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private List<String> categories;
}
