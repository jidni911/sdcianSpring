package com.jidnivai.sdcian.sdcian.entity.shop;

import org.springframework.beans.BeanUtils;

import com.jidnivai.sdcian.sdcian.dto.NewMemoItemDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemoItem {

    public MemoItem(NewMemoItemDto memoItemDto, Product product) {
        BeanUtils.copyProperties(memoItemDto, this);
        this.product = product;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Float price;
}
