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
    private Float serviceCharge;
    private Float deliveryCharge;
    private Float discount;
}
