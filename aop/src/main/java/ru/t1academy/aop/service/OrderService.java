package ru.t1academy.aop.service;

import ru.t1academy.aop.dto.OrderDto;
import ru.t1academy.aop.entity.Order;
import ru.t1academy.aop.model.OrderStatus;

import java.util.List;

public interface OrderService {
    Order getOrderById(Integer id);

    List<Order> getAllOrdersByUserId(Integer userId);

    void deleteOrderById(Integer id);

    Order changeStatusByOrderId(Integer id, OrderStatus status);

    Order createOrder(OrderDto order);
}
