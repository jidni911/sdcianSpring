package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

import com.jidnivai.sdcian.sdcian.enums.DeliveryMethod;
import com.jidnivai.sdcian.sdcian.enums.OrderStatus;
import com.jidnivai.sdcian.sdcian.enums.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private boolean isPaymentCompleted;

    private PaymentMethod paymentMethod;

    private double totalAmount;

    private DeliveryMethod deliveryMethod;

    private String deliveryAddress;

    private String deliveryTime;

    private String deliveryDate;

    private String deliveryInstructions;

    @ManyToOne
    private User user;

    @OneToOne
    private User seller;

    @OneToMany
    private List<OrderItem> orderItems;

    
    
}
