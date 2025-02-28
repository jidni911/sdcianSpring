package com.jidnivai.sdcian.sdcian.payload;

import lombok.Data;

@Data
public class OrderItemField {
    private String productDetails;
    private Float unitPrice;
    private Integer quantity;
    private Float totalPrice;
    private Long orderId;
}
