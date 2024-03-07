package ru.t1academy.aop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;

    private String description;

    private Integer userId;
}
