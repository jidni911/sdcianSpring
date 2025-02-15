package com.jidnivai.sdcian.sdcian.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.dto.NewOrderDto;
import com.jidnivai.sdcian.sdcian.entity.CartItem;
import com.jidnivai.sdcian.sdcian.entity.Order;
import com.jidnivai.sdcian.sdcian.entity.OrderItem;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;
import com.jidnivai.sdcian.sdcian.interfaces.OrderServiceInt;
import com.jidnivai.sdcian.sdcian.repository.OrderItemRepository;
import com.jidnivai.sdcian.sdcian.repository.OrderRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;

@Service
public class OrderService implements OrderServiceInt {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(NewOrderDto newOrderDto, Long userId) {
        Order order = new Order();
        BeanUtils.copyProperties(newOrderDto, order);
        order.setUser(userRepository.findById(userId).orElseThrow());
        List<CartItem> cartItems = cartService.getCartItems(newOrderDto.getOrderItemIds(), userId);
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getDiscountPrice()==0?cartItem.getProduct().getPrice():cartItem.getProduct().getDiscountPrice());
            orderItem.setOrder(order);
            orderItem.setSeller(cartItem.getProduct().getSeller());
            orderItem.setCustomer(order.getUser());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        // Order order = orderRepository.findById(id).orElse(null);
        // if (order != null) {
        //     order.setOrderNumber(orderDetails.getOrderNumber());
        //     order.setStatus(orderDetails.getStatus());
        //     // update other fields as needed
        //     return orderRepository.save(order);
        // }
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<OrderItem> getOrdersForSeller(int page, int size,  OrderStatus status, Long sellerId) {
        User seller = userRepository.findById(sellerId).orElseThrow();
        Page<OrderItem> orderItems = orderItemRepository.findBySellerAndStatus(seller, status, (PageRequest.of(page, size)));
        return orderItems;
    }

    @Override
    public Page<Order> getOrdersByUser(Long userId, int page, int size) {
        return orderRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) {
        // Order order = orderRepository.findById(id).orElse(null);
        // if (order != null) {
        //     order.setStatus(status);
        //     return orderRepository.save(order);
        // }
        return null;
    }

   
}

