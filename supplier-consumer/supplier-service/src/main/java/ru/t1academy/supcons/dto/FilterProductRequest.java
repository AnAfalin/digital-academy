package ru.t1academy.supcons.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FilterProductRequest {
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private List<String> category;
}
