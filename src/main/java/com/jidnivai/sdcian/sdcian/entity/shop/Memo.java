package com.jidnivai.sdcian.sdcian.entity.shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.jidnivai.sdcian.sdcian.dto.MemoDto;
import com.jidnivai.sdcian.sdcian.dto.NewMemoDto;
import com.jidnivai.sdcian.sdcian.dto.NewMemoItemDto;
import com.jidnivai.sdcian.sdcian.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    public Memo(MemoDto memoDto, User buyer, User seller) {
        BeanUtils.copyProperties(memoDto, this);
        this.buyer = buyer;
        this.seller = seller;
    }


    public Memo(NewMemoDto newMemoDto, User buyer2, User seller2, List<Product> products) {
        BeanUtils.copyProperties(newMemoDto, this);
        this.buyer = buyer2;
        this.seller = seller2;
        if (this.memoItems == null) {
            this.memoItems = new ArrayList<>();
        }
        // this.memoItems = newMemoDto.getMemoItems().stream().map(memoItemDto -> new MemoItem(memoItemDto, this)).toList();
        for (NewMemoItemDto newMemoItemDto : newMemoDto.getMemoItems()) {
            Product p = products.stream().filter(product -> product.getId().equals(newMemoItemDto.getProductId())).findFirst().orElse(null);
            memoItems.add(new MemoItem(newMemoItemDto, p));
            // System.out.println("ok");
        }
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer memoNumber;
    private LocalDate date;
    private String buyerName;
    private String buyerAddress;
    private String buyerPhoneNumber;
    private String buyerEmail;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    private Float subtotal;
    private Float discount;
    private Float deliveryCharge;
    private Float serviceCharge;
    private Float total;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemoItem> memoItems;

}
