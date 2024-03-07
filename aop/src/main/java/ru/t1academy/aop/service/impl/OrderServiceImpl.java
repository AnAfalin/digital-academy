package ru.t1academy.aop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t1academy.aop.dto.OrderDto;
import ru.t1academy.aop.entity.Order;
import ru.t1academy.aop.entity.User;
import ru.t1academy.aop.exception.NoOrderExistException;
import ru.t1academy.aop.model.OrderStatus;
import ru.t1academy.aop.repository.OrderRepository;
import ru.t1academy.aop.service.OrderService;
import ru.t1academy.aop.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Order getOrderById(Integer id) {
        return getIfExistOrderById(id);
    }

    @Transactional
    public List<Order> getAllOrdersByUserId(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteOrderById(Integer id) {
        checkExistOrderById(id);
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order changeStatusByOrderId(Integer id, OrderStatus status) {
        Order order = getIfExistOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order createOrder(OrderDto orderDto) {
        User user = userService.getIfExistUserById(orderDto.getUserId());
        Order order = Order.builder()
                .status(OrderStatus.CREATED)
                .description(orderDto.getDescription())
                .user(user)
                .build();
        return orderRepository.save(order);
    }

    private void checkExistOrderById(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new NoOrderExistException("Order with id = {id} already exist");
        }
    }

    private Order getIfExistOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoOrderExistException("Order with id = {id} already exist"));
    }
}

