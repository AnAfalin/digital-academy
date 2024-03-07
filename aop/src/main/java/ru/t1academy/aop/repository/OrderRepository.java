package ru.t1academy.aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1academy.aop.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserId(Integer userId);
}
