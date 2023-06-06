package com.example.onlinestory.service.impl;

import com.example.onlinestory.entity.Order;
import com.example.onlinestory.repository.OrderRepository;
import com.example.onlinestory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }
}
