package com.jidnivai.sdcian.sdcian.entity;

import jakarta.persistence.Entity;
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
public class Rating {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private byte rating;//1-5

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

}
