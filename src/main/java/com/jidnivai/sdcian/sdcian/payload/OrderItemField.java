package com.jidnivai.sdcian.sdcian.payload;

import lombok.Data;

@Data
public class OrderItemField {
    private String productDetails;
    private Double unitPrice;
    private Integer quantity;
    private Double totalPrice;
    private Long orderId;
}
