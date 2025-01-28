package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Order;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;

public interface OrderServiceInt {

    Order getOrder(Long id);

    Page<Order> getOrders(int page, int size);

    Order createOrder(Order orderEntity);

    Order updateOrder(Long id, Order orderEntity);

    void deleteOrder(Long id);

    Page<Order> getOrdersByUser(Long userId, int page, int size);

    Order updateOrderStatus(Long id, OrderStatus status);

}
