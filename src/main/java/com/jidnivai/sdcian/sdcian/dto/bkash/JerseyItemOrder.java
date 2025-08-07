package com.jidnivai.sdcian.sdcian.dto.bkash;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class JerseyItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private JerseyBuyer jerseyBuyer;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<JerseyItemOrderItem> jerseyItemOrderItems;

    private int total;

    private boolean paid;

    private String paidTo;
    private String paymentMethod;
    private String paymentId;
    private String trxId;
    private String status;

    private String buyerName;
    private String buyerAddress;
    private String buyerPhoneNumber;
    private boolean freePickupPoint;
    private String deliveryMethod;
    private int deleveryCharge;
    
}
