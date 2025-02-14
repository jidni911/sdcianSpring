package com.jidnivai.sdcian.sdcian.entity;

import com.jidnivai.sdcian.sdcian.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;

    @ManyToOne
    private Product product;

    private double price;//Current Price

    @ManyToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    
}
