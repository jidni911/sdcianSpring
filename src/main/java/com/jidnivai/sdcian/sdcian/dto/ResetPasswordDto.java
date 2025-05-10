package com.jidnivai.sdcian.sdcian.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResetPasswordDto {
    private String username;
    private String otp;
    private String password;
    private String retypePassword;
}
