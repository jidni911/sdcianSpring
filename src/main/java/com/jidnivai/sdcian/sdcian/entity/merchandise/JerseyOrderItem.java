package com.jidnivai.sdcian.sdcian.entity.merchandise;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class JerseyOrderItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Jersey jersey;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private JerseySize size;
    private String sleeve;
    private int quantity;
    private double price;
}
