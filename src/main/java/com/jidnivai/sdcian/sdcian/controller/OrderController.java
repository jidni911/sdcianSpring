package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.NewOrderDto;
import com.jidnivai.sdcian.sdcian.entity.Order;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;
import com.jidnivai.sdcian.sdcian.interfaces.OrderServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceInt orderServiceInt;

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderServiceInt.getOrder(id);
    }

    @GetMapping
    public Page<Order> getOrders(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return orderServiceInt.getOrders(page, size);
    }

    @PostMapping
    public Order createOrder(@RequestBody NewOrderDto newOrderDto,@AuthenticationPrincipal UserDetailsImpl user) {
        return orderServiceInt.createOrder(newOrderDto, user.getId());
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderEntity) {
        return orderServiceInt.updateOrder(id, orderEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderServiceInt.deleteOrder(id);
    }

    @GetMapping("/user/{userId}")
    public Page<Order> getOrdersByUser(@PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return orderServiceInt.getOrdersByUser(userId, page, size);
    }

    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam("status") OrderStatus status) {
        return orderServiceInt.updateOrderStatus(id, status);
    }

}
