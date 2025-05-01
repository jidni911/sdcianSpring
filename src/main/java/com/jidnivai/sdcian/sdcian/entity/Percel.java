package com.jidnivai.sdcian.sdcian.entity;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.storage.Image;
import com.jidnivai.sdcian.sdcian.enums.PercelStatus;

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
@ToString
@Getter
@Setter
public class Percel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Float weight;
    private Float COD;
    private Float DC;
    private double distance;

    @OneToMany
    private List<Image> images;

    @ManyToOne
    private User sender;
    private String pickupInstructions;
    private String pickupTime;

    private String receiverName;
    private String recieverPhone;
    private String recieverAddress;
    private String deliveryInstructions;
    private String deliveryTime;

    private PercelStatus status;

    @ManyToOne
    private User rider;
}
