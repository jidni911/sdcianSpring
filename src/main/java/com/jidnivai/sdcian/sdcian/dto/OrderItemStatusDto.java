package com.jidnivai.sdcian.sdcian.dto;

import com.jidnivai.sdcian.sdcian.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemStatusDto {
	private Long id;
	private OrderStatus currentStatus;
	private boolean continuation;
	
	
}
