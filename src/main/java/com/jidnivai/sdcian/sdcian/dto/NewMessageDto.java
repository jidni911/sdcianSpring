package com.jidnivai.sdcian.sdcian.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewMessageDto {
    private String messege;
    private Long chatId;
}
