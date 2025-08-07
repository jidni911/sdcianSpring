package com.jidnivai.sdcian.sdcian.dto.bkash;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CreateResponse {
    @Id
    private String paymentID;
    private String bkashURL;
    private String callbackURL;
    private String successCallbackURL;
    private String failureCallbackURL;
    private String cancelledCallbackURL;
    private String amount;
    private String intent;
    private String currency;
    private String paymentCreateTime;
    private String transactionStatus;
    private String merchantInvoiceNumber;
    private String statusCode;
    private String statusMessage;
}
