package ru.t1academy.aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.t1academy.aop.dto.OrderDto;
import ru.t1academy.aop.entity.Order;
import ru.t1academy.aop.model.OrderStatus;
import ru.t1academy.aop.service.OrderService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    public Order createOrder(@RequestBody OrderDto order) {
        return orderService.createOrder(order);
    }

    @PatchMapping("/{id}")
    public Order changeStatusByOrderId(@PathVariable Integer id, @RequestBody OrderStatus status) {
        return orderService.changeStatusByOrderId(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrdersByUserId(@RequestParam Integer userId) {
        return orderService.getAllOrdersByUserId(userId);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }
}
