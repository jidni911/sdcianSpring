package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.NewOrderDto;
import com.jidnivai.sdcian.sdcian.entity.Order;
import com.jidnivai.sdcian.sdcian.entity.OrderItem;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;

public interface OrderServiceInt {

    Order getOrder(Long id);

    Page<OrderItem> getOrdersForSeller(int page, int size,  OrderStatus status, Long userId);

    Order createOrder(NewOrderDto newOrderDto, Long userId);

    Order updateOrder(Long id, Order orderEntity);

    void deleteOrder(Long id);

    Page<Order> getOrdersByUser(Long userId, int page, int size);

    Order updateOrderStatus(Long id, OrderStatus status);

}
