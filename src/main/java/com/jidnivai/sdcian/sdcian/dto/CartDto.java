package com.jidnivai.sdcian.sdcian.dto;

import java.util.List;

import com.jidnivai.sdcian.sdcian.entity.shop.CartItem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDto {

    private Long id;

    private UserDto userDto;

    private List<CartItem> items;
}
