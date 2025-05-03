package com.jidnivai.sdcian.sdcian.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.jidnivai.sdcian.sdcian.entity.shop.Memo;
import com.jidnivai.sdcian.sdcian.entity.shop.MemoItem;

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
public class MemoDto {

    public MemoDto(Memo memo) {
        BeanUtils.copyProperties(memo, this);
        this.sellerId = memo.getSeller().getId();
        if (memo.getBuyer() != null) {
            this.buyerId = memo.getBuyer().getId();
        }
    }

    private Long id;

    private Integer memoNumber;
    private LocalDate date;
    private String buyerName;
    private String buyerAddress;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private Long sellerId;
    private Long buyerId;

    private Float subtotal;
    private Float discount;
    private Float deliveryCharge;
    private Float serviceCharge;
    private Float total;

    private List<MemoItem> memoItems;
}
