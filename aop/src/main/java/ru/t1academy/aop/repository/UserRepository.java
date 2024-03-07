package ru.t1academy.aop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1academy.aop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
