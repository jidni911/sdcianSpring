package com.jidnivai.sdcian.sdcian.entity.merchandise;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jidnivai.sdcian.sdcian.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class JerseyOrder {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    private String name;
    private String phone;
    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<JerseyOrderItem> items;
    private double deliveryCharge;
    private String address;
    @JsonIgnore
    private boolean pending = true;
    // private String paymentMethod;
    // private String paymentNumber;
    // private String transactionId;
    // private int payment;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    
    
    
}