package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.jidnivai.sdcian.sdcian.entity.Order;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;
import com.jidnivai.sdcian.sdcian.interfaces.OrderServiceInt;
import com.jidnivai.sdcian.sdcian.repository.OrderRepository;

@Service
public class OrderService implements OrderServiceInt {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setOrderNumber(orderDetails.getOrderNumber());
            order.setStatus(orderDetails.getStatus());
            // update other fields as needed
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Order> getOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Order> getOrdersByUser(Long userId, int page, int size) {
        return orderRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }
        return null;
    }

   
}

