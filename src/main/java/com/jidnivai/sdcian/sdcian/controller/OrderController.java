package com.jidnivai.sdcian.sdcian.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import com.jidnivai.sdcian.sdcian.dto.ConfirmOrderRequestDto;
import com.jidnivai.sdcian.sdcian.dto.NewOrderDto;
import com.jidnivai.sdcian.sdcian.dto.OrderItemStatusDto;
import com.jidnivai.sdcian.sdcian.entity.Order;
import com.jidnivai.sdcian.sdcian.entity.OrderItem;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;
import com.jidnivai.sdcian.sdcian.interfaces.OrderServiceInt;
import com.jidnivai.sdcian.sdcian.payload.response.OperationResult;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceInt orderServiceInt;

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderServiceInt.getOrder(id);
    }

    @GetMapping("memo/{orderItemId}")
    public List<OrderItem> getOrderItems(@PathVariable Long orderItemId, @AuthenticationPrincipal UserDetailsImpl user) {
        return orderServiceInt.getOrderItems(orderItemId, user.getId());
    }
    

    @GetMapping("/seller")
    public Page<OrderItem> getOrdersForSeller(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = true) OrderStatus status,
            @AuthenticationPrincipal UserDetailsImpl user
            ) {
        return orderServiceInt.getOrdersForSeller(page, size, status, user.getId());
    }

    @PostMapping
    public ResponseEntity<byte[]> createOrder(@RequestBody NewOrderDto newOrderDto,@AuthenticationPrincipal UserDetailsImpl user) throws JRException, IOException {
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

    @PostMapping("updateStatus")
    public OperationResult updateOrderItemStatus(
            @RequestBody(required = true) OrderItemStatusDto orderItemStatusDto,
            @AuthenticationPrincipal UserDetailsImpl user
    ) {
        // System.out.println(orderItemStatusDto);
        return orderServiceInt.updateOrderItemStatus(orderItemStatusDto, user.getId());
    }

    
    @PostMapping("/confirm")
    public ResponseEntity<byte[]> confirmOrder(@RequestBody ConfirmOrderRequestDto confirmOrderRequestDto, @AuthenticationPrincipal UserDetailsImpl user) throws JRException, IOException  {
        return orderServiceInt.confirmOrder(confirmOrderRequestDto, user.getId());
    }
    
}
