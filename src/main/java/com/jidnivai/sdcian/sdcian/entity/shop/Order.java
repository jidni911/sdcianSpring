package com.jidnivai.sdcian.sdcian.entity.shop;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    String name;
    String phoneNumber;
    String userName;
    String email;
    // String phone;
    String address;
    String paymentMethod;
    String paymentNumber;
    String transactionId;
    Integer payment =0;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<OrderItem> orderItems;

    private LocalDateTime createdAt;

    @Transient
    private Float serviceCharge;

    @Transient
    private Float deliveryCharge;

    @Transient
    private Float discount;

    @Transient
    private Float subTotal;

    @Transient
    private Float total;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    

}
