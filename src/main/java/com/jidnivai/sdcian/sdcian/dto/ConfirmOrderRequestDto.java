package com.jidnivai.sdcian.sdcian.dto;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.OrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class ConfirmOrderRequestDto {
    private List<OrderItem> orderItems;
    private double serviceCharge;
    private double deliveryCharge;
    private double discount;
}
