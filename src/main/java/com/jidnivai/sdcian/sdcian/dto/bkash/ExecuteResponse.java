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
public class ExecuteResponse {
    private String statusCode;
    private String statusMessage;
    @Id
    private String paymentID;
    private String payerReference;
    private String customerMsisdn;
    private String trxID;
    private String amount;
    private String transactionStatus;
    private String paymentExecuteTime;
    private String currency;
    private String intent;
    private String merchantInvoiceNumber;
}
