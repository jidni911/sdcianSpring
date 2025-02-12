package com.jidnivai.sdcian.sdcian.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
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
    @ToString.Exclude
    @JsonIgnore
    private Order order;

    @Transient
    public Long getOrderId(){
        return order != null ? order.getId() : null;
    }

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    
}
