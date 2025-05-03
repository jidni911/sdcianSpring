package com.jidnivai.sdcian.sdcian.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.Order;
import com.jidnivai.sdcian.sdcian.entity.shop.OrderItem;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findBySellerAndStatus(User seller, OrderStatus status, PageRequest of);

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByOrderAndSeller(Order order, User seller);

    List<OrderItem> findByOrderAndSellerAndStatus(Order order, User seller, OrderStatus processing);
}
