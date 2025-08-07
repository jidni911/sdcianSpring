package com.jidnivai.sdcian.sdcian.dto.bkash;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String jerseyBuyerNumber;
    private Boolean paid;
    private Integer total;
    private String status;
    private String paidTo;
    private String paymentMethod;
    private String buyerAddress;
    private String buyerName;
    private String buyerPhoneNumber;
    private Integer deleveryCharge;
    private boolean freePickupPoint;
    private String paymentId;
    private String trxId;
    private String deliveryMethod;
    private Integer quantity;
    private Integer rate;
    private String size;
    private Long jerseyItemId;
    private String nameOnJersey;
    private String nickName;
    // getters & setters
}
