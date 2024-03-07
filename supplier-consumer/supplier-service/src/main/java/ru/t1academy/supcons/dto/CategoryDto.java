package ru.t1academy.supcons.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    private String name;
}
