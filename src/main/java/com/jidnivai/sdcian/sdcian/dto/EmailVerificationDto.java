package com.jidnivai.sdcian.sdcian.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailVerificationDto {
    private String email;
    private String otp;
}
