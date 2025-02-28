package com.jidnivai.sdcian.sdcian.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewOrderDto {


    String name;
    String phoneNumber;
    String userName;
    String email;
    // String phone;
    String address;
    String paymentMethod;
    String paymentNumber;
    String transactionId;
    private List<Long> orderItemIds;
}
