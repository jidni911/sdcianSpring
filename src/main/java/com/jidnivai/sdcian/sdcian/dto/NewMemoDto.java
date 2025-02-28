package com.jidnivai.sdcian.sdcian.dto;


import java.time.LocalDate;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewMemoDto {


    private Integer memoNumber;
    private LocalDate date;
    private String buyerName;
    private String buyerAddress;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private Long buyerId;

    private Float subtotal;
    private Float discount;
    private Float deliveryCharge;
    private Float serviceCharge;
    private Float total;

    private List<NewMemoItemDto> memoItems;//memoItems


}
