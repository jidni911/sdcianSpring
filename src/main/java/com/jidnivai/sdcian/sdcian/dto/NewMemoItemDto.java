package com.jidnivai.sdcian.sdcian.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewMemoItemDto {

    private Long productId;
    private Integer quantity;
    private Float price;
}
