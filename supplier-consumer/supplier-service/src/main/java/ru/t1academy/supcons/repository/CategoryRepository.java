package ru.t1academy.supcons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1academy.supcons.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
